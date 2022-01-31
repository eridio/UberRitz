package fr.isen.PEDEPRAT.androidrestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import fr.isen.PEDEPRAT.androidrestaurant.model.Dish
import fr.isen.PEDEPRAT.androidrestaurant.model.DishModel

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        findViewById<TextView>(R.id.detailtitle).text = (intent.getSerializableExtra("dish") as DishModel).name_fr
    }
}