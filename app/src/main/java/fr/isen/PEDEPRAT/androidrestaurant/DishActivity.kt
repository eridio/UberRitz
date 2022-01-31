package fr.isen.PEDEPRAT.androidrestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import fr.isen.PEDEPRAT.androidrestaurant.databinding.ActivityDishBinding
import fr.isen.PEDEPRAT.androidrestaurant.model.DishModel
import fr.isen.PEDEPRAT.androidrestaurant.model.DishResult
import org.json.JSONObject

class DishActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDishBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityDishBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mainDishTitle.text = intent.getStringExtra("category_type")

        binding.dishList.layoutManager = LinearLayoutManager(this)

        val textView = findViewById<TextView>(R.id.httpresponse)

        var category: String? = null
        if (intent.hasExtra("category_type")){
            category = intent.getStringExtra("category_type")
        }


        val queue = Volley.newRequestQueue(this)
        val url = "http://test.api.catering.bluecodegames.com/menu"
        val jsonObject = JSONObject()
        jsonObject.put("id_shop",1)

        val request = JsonObjectRequest (
            Request.Method.POST,url,jsonObject, {
                response ->
                //dans ce block la on est synchro avec le web service

                val gson = Gson()
                val dishResult = gson.fromJson(response.toString(), DishResult::class.java)
                //Log.d("","$dishResult")
                //filter

               displayDishes(dishResult.data.firstOrNull {it.name_fr == category }?.items ?: listOf())


            }, { textView.text = "volley error : $it" }
                )

        request.retryPolicy = DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,0,1f)
        queue.add(request)





    }

    private fun displayDishes (dishResult: List<DishModel>){
        val recyclerview = binding.dishList
        recyclerview.layoutManager = LinearLayoutManager(this)
        val adapter = DishAdapter(dishResult,this)
        recyclerview.adapter = adapter
    }

    fun onCellClickListener(data: DishModel){
        val intent = Intent(this,DetailActivity::class.java)
        intent.putExtra("dish", data)
        startActivity(intent)
    }
}
