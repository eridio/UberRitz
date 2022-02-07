package fr.isen.PEDEPRAT.androidrestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.isen.PEDEPRAT.androidrestaurant.databinding.ActivityPanierBinding

private lateinit var binding: ActivityPanierBinding
class PanierActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPanierBinding.inflate(layoutInflater)
        setContentView(binding.root)

        reload_panier()
        binding.commandButton.setOnClickListener{

            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

    }

    private fun reload_panier(){

    }
}
