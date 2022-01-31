package fr.isen.PEDEPRAT.androidrestaurant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import fr.isen.PEDEPRAT.androidrestaurant.databinding.FragmentImagesDetailBinding

private lateinit var binding : FragmentImagesDetailBinding

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

        arguments?.getString("URL")?.let {
            Picasso.get().load(it)
                .placeholder(R.drawable.ic_launcher_background)
                .into(binding.photo)
        }

    }
    companion object{
        fun new(url:String?) =
            DetailImagesFragment().apply { arguments = Bundle().apply { putString("URL",url) } }
    }
}