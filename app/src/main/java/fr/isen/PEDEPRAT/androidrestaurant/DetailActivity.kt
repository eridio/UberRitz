package fr.isen.PEDEPRAT.androidrestaurant

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.view.isVisible
import fr.isen.PEDEPRAT.androidrestaurant.databinding.ActivityDetailBinding
import fr.isen.PEDEPRAT.androidrestaurant.model.DishModel


private lateinit var binding : ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dish = intent.getSerializableExtra("dish") as DishModel
        binding.detailtitle.text = dish.name_fr
        binding.priceDetail.text = dish.prices[0].price + "€"
       // binding.detailsIngredients.text = dish.get_all_ingredients().toString()
        dish.get_all_pictures()?.let{
            binding.imageDetailsDefault.isVisible = false
            binding.imagesDetail.adapter = DetailAdapter(this,it)
        }?: run {
            binding.imageDetailsDefault.isVisible = true
            binding.imagesDetail.isVisible = false
        }

    }
}