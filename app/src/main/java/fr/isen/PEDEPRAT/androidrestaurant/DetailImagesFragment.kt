package fr.isen.PEDEPRAT.androidrestaurant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import fr.isen.PEDEPRAT.androidrestaurant.databinding.FragmentImagesDetailBinding

private lateinit var binding : FragmentImagesDetailBinding
//partie d'activitée
//decoupage d'activité : il ont leur propre cycle de vie
//sur une activité = peut avoir plusieurs fragments
class DetailImagesFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentImagesDetailBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getString("URL")?.let {  //si les arguments sont present alors exec apres le let
            Picasso.get().load(it)
                .placeholder(R.drawable.ic_launcher_background)
                .into(binding.photo)
        }

    }
    companion object{//fun static peut etre appelé sans instance de class en java
        //companion object = fun static de java
        fun new(url:String?) =
            DetailImagesFragment().apply { arguments = Bundle().apply { putString("URL",url) } }
    }
}