package fr.isen.PEDEPRAT.androidrestaurant

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import fr.isen.PEDEPRAT.androidrestaurant.databinding.ActivityOrderBinding
import fr.isen.PEDEPRAT.androidrestaurant.model.RegisterResult
import org.json.JSONObject
import java.io.File

private lateinit var binding: ActivityOrderBinding
class OrderActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        order()
        binding.ordertitle.text = "OrDeR"
        binding.buttonRetour.setOnClickListener{
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }
    }


    private fun order() {

        val queue = Volley.newRequestQueue(this)
        val url = "http://test.api.catering.bluecodegames.com/user/order"

        val jsonObject = JSONObject()   //id_shop, id_user et msg. Le paramètre msg contiendra le json sérialisé de votre panier.

        val panier = File(cacheDir.absolutePath + "panier.json").readText()
        jsonObject.put("msg", panier)

        jsonObject.put("id_shop", 1)

        val user_id = getSharedPreferences("app_preferences", Context.MODE_PRIVATE).getString("user_id", "")
        jsonObject.put("id_user", user_id)


        val request = JsonObjectRequest(
            Request.Method.POST, url, jsonObject, { response ->
                //dans ce block la on est synchro avec le web service

                displayAnimation()

            }, {
                Toast.makeText(applicationContext, "Erreur $it", Toast.LENGTH_SHORT)
                    .show()
                notdisplayAnimation()
            }
        )

        queue.add(request)
    }

    private fun displayAnimation() {
        binding.deliveryMan.isVisible = true
        binding.orderLoading.isVisible = false
        binding.commandOk.isVisible = true
        binding.erreur.isVisible = false
    }
    private fun notdisplayAnimation() {
        binding.deliveryMan.isVisible = false
        binding.orderLoading.isVisible = false
        binding.commandOk.isVisible = false
        binding.erreur.isVisible = true
    }


}