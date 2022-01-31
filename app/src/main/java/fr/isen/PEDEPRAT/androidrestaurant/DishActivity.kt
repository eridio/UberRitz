package fr.isen.PEDEPRAT.androidrestaurant

import android.app.DownloadManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import fr.isen.PEDEPRAT.androidrestaurant.databinding.ActivityDishBinding
import fr.isen.PEDEPRAT.androidrestaurant.model.Dish
import org.json.JSONObject
import java.lang.Exception

class DishActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDishBinding

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
            Request.Method.POST,url,jsonObject,Response.Listener {
                response ->
                    try {
                        textView.text = "Response : $response"
                    }catch (e:Exception){
                        textView.text = "Volley error : $e"
                    }
            },Response.ErrorListener { textView.text = "volley error : $it" }
                )

        request.retryPolicy = DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,0,1f)
        queue.add(request)

        val dishes = listOf(
            Dish("tomates a la provencale", R.drawable.ic_launcher_background,"12$"),
            Dish("Burger maison", R.drawable.ic_launcher_background,"15$"),

        )
        binding.dishList.adapter = DishAdapter(dishes) {
            val intent = Intent(this,DetailActivity::class.java)
            intent.putExtra("dish", it)
            startActivity(intent)
        }
    }
}