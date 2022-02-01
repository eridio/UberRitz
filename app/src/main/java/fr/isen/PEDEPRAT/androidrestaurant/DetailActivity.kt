package fr.isen.PEDEPRAT.androidrestaurant

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import fr.isen.PEDEPRAT.androidrestaurant.databinding.ActivityDetailBinding
import fr.isen.PEDEPRAT.androidrestaurant.model.DishModel
import java.io.File


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

        binding.detailTotalButton.text = "Total  " + dish.prices[0].price + "€"

       binding.detailsIngredients.text = dish.ingredients.joinToString(", ") {it.name_fr} //convert a list to a String with a separator


        dish.get_all_pictures()?.let{
            binding.imageDetailsDefault.isVisible = false
            binding.imagesDetail.adapter = DetailAdapter(this,it)
        }?: run {
            binding.imageDetailsDefault.isVisible = true
            binding.imagesDetail.isVisible = false
        }

        //Plus/minus/total
        var quantity = 1
        binding.detailQuantity.text = quantity.toString()
        binding.plusButton.setOnClickListener{
            quantity+=1
            binding.detailQuantity.text = quantity.toString()
            binding.detailTotalButton.text = "Total  " + "${quantity * (dish.prices[0].price).toInt()}" + "€"
        }
        binding.minusButton.setOnClickListener{
            if (quantity>=2){
                quantity-=1
                binding.detailQuantity.text = quantity.toString()
                binding.detailTotalButton.text = "Total  " + "${quantity * (dish.prices[0].price).toInt()}" + "€"
            }
        }

        binding.detailTotalButton.setOnClickListener{
            if (quantity==1) {Toast.makeText(applicationContext,"votre ${dish.name_fr} a bien été ajouté à votre panier", Toast.LENGTH_SHORT).show() }
            else{Toast.makeText(applicationContext,"vos ${dish.name_fr} ont bien été ajouté à votre panier", Toast.LENGTH_SHORT).show()}

            createJsonPanier(quantity,dish)


        }


    }

    private fun createJsonPanier(quantity : Int, dish: DishModel){
        val json = File(cacheDir.absolutePath + "panier.json")
        if (json.exists()){
            val readJson = json.readText()
        }



    }

}