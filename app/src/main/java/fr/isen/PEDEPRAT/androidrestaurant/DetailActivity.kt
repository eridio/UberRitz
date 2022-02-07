package fr.isen.PEDEPRAT.androidrestaurant

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import com.google.gson.GsonBuilder
import fr.isen.PEDEPRAT.androidrestaurant.databinding.ActivityDetailBinding
import fr.isen.PEDEPRAT.androidrestaurant.model.DishModel
import fr.isen.PEDEPRAT.androidrestaurant.model.Panier
import fr.isen.PEDEPRAT.androidrestaurant.model.ItemPanier
import java.io.File


private lateinit var binding : ActivityDetailBinding


class DetailActivity : AppCompatActivity() {
    lateinit var sharedPreferences : SharedPreferences

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

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

            JsonPanier(quantity,dish)




            //proposer un button update ou simplement ajouter?


        }


    }

    private fun JsonPanier(quantity : Int, dish: DishModel){
        val json = File(cacheDir.absolutePath + "panier.json")
        if (json.exists()){
            val readJson = json.readText()
            if (readJson.isNotEmpty()) {
                val panier = GsonBuilder().create().fromJson(readJson, Panier::class.java)
                updatePanier(panier, quantity, dish)
            }
            else {updatePanier(null, quantity, dish)
            }
        }
        else {
            updatePanier(null, quantity, dish)
        }
    }

    private fun updatePanier(panier: Panier?, quantity: Int,dish: DishModel){
        val panier_create = panier?: Panier(mutableListOf())
        panier_create.items.add(ItemPanier(quantity,dish))
        updateUserPreferences(panier_create)
        //lire le json
        //deserializer la list
        //reserializer
        File(cacheDir.absolutePath + "panier.json").writeText(GsonBuilder().create().toJson(panier_create))
        invalidateOptionsMenu()
    }
    private fun updateUserPreferences(panier : Panier) {
        val edit=sharedPreferences.edit()
        edit.putInt("panier_count", panier.items.sumOf { it.quantity })
        edit.apply()
    }
}