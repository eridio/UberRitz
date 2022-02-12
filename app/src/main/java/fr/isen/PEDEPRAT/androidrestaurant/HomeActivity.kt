package fr.isen.PEDEPRAT.androidrestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Toast
import fr.isen.PEDEPRAT.androidrestaurant.databinding.ActivityHomeBinding


class HomeActivity : AppCompatActivity() {
    //renomer le projet en uberritz a la place de android restaurant

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.starters.setOnClickListener{
            Toast.makeText(applicationContext,"Entrées",Toast.LENGTH_SHORT).show()
            changeActivity(getString(R.string.home_starters))
        }
        binding.maps.setOnClickListener{
            //startActivity(Intent(this, MapsActivity::class.java))
            //finish()

        }
        binding.dish.setOnClickListener{
            Toast.makeText(applicationContext,"plats",Toast.LENGTH_SHORT).show()
            changeActivity(getString(R.string.home_dish))
        }
        binding.desserts.setOnClickListener{
            Toast.makeText(applicationContext,"Desserts",Toast.LENGTH_SHORT).show()
            changeActivity(getString(R.string.home_desserts))
        }

        binding.panierHome.setOnClickListener{

            startActivity(Intent(this, PanierActivity::class.java))
            finish()
        }

        binding.commandes.setOnClickListener{
            startActivity(Intent(this, OderPassedActivity::class.java))
            finish()
        }



    }
    private fun changeActivity(category : String) {
        val intent = Intent(this, DishActivity::class.java)
        intent.putExtra("category_type",category)
        startActivity(intent)
    }
}

