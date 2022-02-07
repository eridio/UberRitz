package fr.isen.PEDEPRAT.androidrestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import fr.isen.PEDEPRAT.androidrestaurant.databinding.ActivityRegisterBinding
import fr.isen.PEDEPRAT.androidrestaurant.model.RegisterResult
import org.json.JSONObject

private lateinit var binding : ActivityRegisterBinding
class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.chooseLoginButton.setOnClickListener{
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        binding.title.text = "Créer un Compte :"

        binding.RegisterButton.setOnClickListener{
            registerUser()
        }
    }

    private fun registerUser(){
        val queue = Volley.newRequestQueue(this)
        val url = "http://test.api.catering.bluecodegames.com/user/register"
        val data_to_send = listOf(
            binding.firstName.text,binding.lastName.text,binding.address.text,binding.email.text,binding.Password.text
        )

        if (data_to_send.any{ it?.trim().isNullOrEmpty()}) {
            Toast.makeText(applicationContext,"Veillez à remplir correctement tous les champs", Toast.LENGTH_SHORT).show()
        }

        else {
                val jsonObject = JSONObject()
                jsonObject.put("id_shop", 1)
                jsonObject.put("firstname", binding.firstName.text)
                jsonObject.put("lastname", binding.lastName.text)
                jsonObject.put("address", binding.address.text)
                jsonObject.put("email", binding.email.text)
                jsonObject.put("password", binding.Password.text)

                val request = JsonObjectRequest(
                    Request.Method.POST, url, jsonObject, { response ->
                        //dans ce block la on est synchro avec le web service

                        val gson = Gson()
                        val register_result =
                            gson.fromJson(response.toString(), RegisterResult::class.java)
                        Toast.makeText(applicationContext,"Vous avez bien été enregistré", LENGTH_SHORT).show()

                        startActivity(Intent(this, LoginActivity::class.java))
                        finish()

                    }, {
                        Toast.makeText(applicationContext, "il manque quelque chose..", LENGTH_SHORT).show()
                    }
                )

                request.retryPolicy = DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS, 0, 1f)
                queue.add(request)
        }

    }
}