package fr.isen.PEDEPRAT.androidrestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import fr.isen.PEDEPRAT.androidrestaurant.databinding.ActivityDishBinding
import fr.isen.PEDEPRAT.androidrestaurant.model.Dish
import fr.isen.PEDEPRAT.androidrestaurant.model.DishResult
import org.json.JSONObject
import java.lang.Exception

class DishActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDishBinding
    private val name_fr: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityDishBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mainDishTitle.text = intent.getStringExtra("category_type")

        binding.dishList.layoutManager = LinearLayoutManager(this)

        val textView = findViewById<TextView>(R.id.httpresponse)

        val queue = Volley.newRequestQueue(this)
        val url = "http://test.api.catering.bluecodegames.com/menu"
        val jsonObject = JSONObject()
        jsonObject.put("id_shop",1)

        val request = JsonObjectRequest (
            Request.Method.POST,url,jsonObject, {
                response ->
                //dans ce block la on est synchro avec le web service

                val gson = Gson()
                var dishResult = gson.fromJson(response.toString(), DishResult::class.java)
                Log.d("","$dishResult")


                binding.dishList.adapter = DishAdapter(dishResult.data[0].items) {
                    val intent = Intent(this,DetailActivity::class.java)
                    intent.putExtra("dish", it)
                    startActivity(intent)
                }


            }, { textView.text = "volley error : $it" }
                )

        request.retryPolicy = DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,0,1f)
        queue.add(request)





    }
}
