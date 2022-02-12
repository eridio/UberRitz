package fr.isen.PEDEPRAT.androidrestaurant

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import fr.isen.PEDEPRAT.androidrestaurant.databinding.ActivityOderPassedBinding
import fr.isen.PEDEPRAT.androidrestaurant.model.Dish
import fr.isen.PEDEPRAT.androidrestaurant.model.DishModel
import fr.isen.PEDEPRAT.androidrestaurant.model.DishResult
import fr.isen.PEDEPRAT.androidrestaurant.model.RegisterResult
import org.json.JSONObject
import java.io.File

private lateinit var binding: ActivityOderPassedBinding

class OderPassedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOderPassedBinding.inflate(layoutInflater)
        setContentView(binding.root)


        var url = "http://test.api.catering.bluecodegames.com/listorders"

        val queue = Volley.newRequestQueue(this)

        val jsonObject = JSONObject()
        jsonObject.put("id_shop", 1)
        val user_id = getSharedPreferences("app_preferences", Context.MODE_PRIVATE).getString("user_id", "")
        jsonObject.put("id_user", user_id)

        val request = JsonObjectRequest(
            Request.Method.POST, url, jsonObject, { response ->
                //dans ce block la on est synchro avec le web service

                //val gson = Gson()
              //  val result = gson.fromJson(response.toString(), DishResult::class.java)
            binding.orderpassed.text = response.toString()

            }, {
                Toast.makeText(applicationContext, "Erreur $it", Toast.LENGTH_SHORT)
                    .show()
            }
        )

        queue.add(request)
    }

}