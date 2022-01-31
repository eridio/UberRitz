package fr.isen.PEDEPRAT.androidrestaurant

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class DetailAdapter ( activity : AppCompatActivity, private val images: List<String?>): FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return images.size
    }

    override fun createFragment(position: Int): Fragment {
        return DetailImagesFragment.new(images[position])
    }
}