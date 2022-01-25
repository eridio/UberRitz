package fr.isen.PEDEPRAT.androidrestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.R
import android.app.Activity
import android.view.View
import android.widget.Button
import android.widget.Toast
import fr.isen.PEDEPRAT.androidrestaurant.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.buttonEntrees.setOnClickListener{
            Toast.makeText(self,"entree",Toast.LENGTH_SHORT).show()
        }
        binding.button2.setOnClickListener{
            Toast.makeText(self,"plats",Toast.LENGTH_SHORT).show()
        }
        binding.button3.setOnClickListener{
            Toast.makeText(self,"desert",Toast.LENGTH_SHORT).show()
        }


    }
}

