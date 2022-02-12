package fr.isen.PEDEPRAT.androidrestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.isen.PEDEPRAT.androidrestaurant.databinding.ActivityEmptyPanierBinding
import fr.isen.PEDEPRAT.androidrestaurant.databinding.ActivityHomeBinding

private lateinit var binding: ActivityEmptyPanierBinding

class EmptyPanierActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmptyPanierBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.commandButtonEmpty.setOnClickListener{
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }
    }
}