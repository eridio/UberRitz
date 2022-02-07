package fr.isen.PEDEPRAT.androidrestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.isen.PEDEPRAT.androidrestaurant.databinding.ActivityOrderBinding

private lateinit var binding: ActivityOrderBinding
class OrderActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ordertitle.text = "OrDeR"
    }
}