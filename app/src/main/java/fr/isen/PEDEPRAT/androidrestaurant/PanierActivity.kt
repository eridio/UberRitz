package fr.isen.PEDEPRAT.androidrestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import fr.isen.PEDEPRAT.androidrestaurant.databinding.ActivityPanierBinding
import fr.isen.PEDEPRAT.androidrestaurant.model.Panier
import java.io.File

private lateinit var binding: ActivityPanierBinding
class PanierActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPanierBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadPanier()

        binding.commandButton.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

    }

    private fun loadPanier() {
        val jsonFile = File(cacheDir.absolutePath + "panier.json")

        var hasItemsInBasket = false
        if (jsonFile.exists()) {
            val dataJson = jsonFile.readText()
            if (dataJson.isNotEmpty()) {
                val basket = GsonBuilder().create().fromJson(dataJson, Panier::class.java)
                if (basket.items.isNotEmpty()) {
                    hasItemsInBasket = true

                    //display
                    binding.panierList.layoutManager = LinearLayoutManager(this)
                    binding.panierList.adapter = PanierAdapteur(basket.items) {

                        basket.items.remove(it)
                        updatePanier(basket)
                        invalidateOptionsMenu()

                    }
                }
            }
            if (!hasItemsInBasket) {
                displayEmptyBasket()
            }
        }
    }
    private fun updatePanier(panier:Panier){
        //saveDish(panier)
        File(cacheDir.absolutePath + "panier.json").writeText(
            GsonBuilder().create().toJson(panier))
        startActivity(Intent(this, PanierActivity::class.java))
        finish()

    }
    private fun saveDish(panier : Panier) {
    }

    private fun displayEmptyBasket() {
        startActivity(Intent(this, EmptyPanierActivity::class.java))
        finish()
    }

}
