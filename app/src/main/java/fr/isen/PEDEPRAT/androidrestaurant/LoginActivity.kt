package fr.isen.PEDEPRAT.androidrestaurant

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import fr.isen.PEDEPRAT.androidrestaurant.databinding.ActivityLoginBinding
import fr.isen.PEDEPRAT.androidrestaurant.model.RegisterResult
import org.json.JSONObject

private lateinit var binding: ActivityLoginBinding
class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        test_login_with_preferences()

        binding.chooseRegisterButton.setOnClickListener{
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()
        }

        binding.title2.text = "Se Connecter"

        binding.loginButton.setOnClickListener{
            login()
        }

    }

    private fun login() {

        val queue = Volley.newRequestQueue(this)
        val url = "http://test.api.catering.bluecodegames.com/user/login"

        val jsonObject = JSONObject()
        jsonObject.put("id_shop", 1)
        jsonObject.put("email", binding.emailLogin.text)
        jsonObject.put("password", binding.passwordLogin.text)

        val request = JsonObjectRequest(
            Request.Method.POST, url, jsonObject, { response ->
                //dans ce block la on est synchro avec le web service


                val gson = Gson()
                val register_result = gson.fromJson(response.toString(), RegisterResult::class.java)


                //preferences
                val edit=getSharedPreferences("app_preferences",Context.MODE_PRIVATE).edit()
                edit.putString("email", binding.emailLogin.text.toString())
                edit.putString("user_id", register_result.data.id)
                edit.putString("password", binding.passwordLogin.text.toString())
                edit.apply()


                //binding.title2.text = register_result.toString()
                startActivity(Intent(this, OrderActivity::class.java))
                finish()
            }, {
                Toast.makeText(applicationContext, "Erreur lors du login", LENGTH_SHORT).show()
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        )

        request.retryPolicy = DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS, 0, 1f)
        queue.add(request)

    }

    private fun test_login_with_preferences(){
        val email_returned =
            getSharedPreferences("app_preferences", Context.MODE_PRIVATE).getString("email", "")
        val password_returned =
            getSharedPreferences("app_preferences", Context.MODE_PRIVATE).getString("password", "")


        val queue = Volley.newRequestQueue(this)
        val url = "http://test.api.catering.bluecodegames.com/user/login"

        val jsonObject = JSONObject()
        jsonObject.put("id_shop", 1)
        jsonObject.put("email", email_returned.toString())
        jsonObject.put("password", password_returned.toString())

        val request = JsonObjectRequest(
            Request.Method.POST, url, jsonObject, { response ->
                //dans ce block la on est synchro avec le web service

                val gson = Gson()
                val register_result =
                    gson.fromJson(response.toString(), RegisterResult::class.java)

                startActivity(Intent(this, OrderActivity::class.java))


            }, {
                val clean = getSharedPreferences("app_preferences",Context.MODE_PRIVATE)
                clean.edit().remove("email").commit()
                clean.edit().remove("password").commit()
            }
        )

        request.retryPolicy = DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS, 0, 1f)
        queue.add(request)

    }
}