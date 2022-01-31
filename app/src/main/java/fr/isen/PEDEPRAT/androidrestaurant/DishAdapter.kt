package fr.isen.PEDEPRAT.androidrestaurant

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.isen.PEDEPRAT.androidrestaurant.databinding.CardViewDesignBinding
import fr.isen.PEDEPRAT.androidrestaurant.model.DishModel

class DishAdapter(val dishes: List<DishModel>, private val cellClickListener: DishActivity): RecyclerView.Adapter<DishAdapter.DishViewHolder>() {

    class DishViewHolder(val binding: CardViewDesignBinding):RecyclerView.ViewHolder(binding.root){
        val itemimage = binding.itemimage
        val itemName = binding.itemName
        val itemPrice = binding.itemPrice
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishViewHolder {

            val binding = CardViewDesignBinding.inflate((LayoutInflater.from(parent.context)),parent,false)
            return DishViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: DishViewHolder, position: Int) {

        val dish = dishes[position]

        holder.itemName.text = dish.name_fr
       //Log.d("", dish.images[0].toString())
        //load images for the card_view design
        Picasso.get().load(dishes[position].getFirstPicture())
            .error(R.drawable.ic_launcher_background)
            .placeholder(R.drawable.ic_launcher_background)
            .into(holder.itemimage)



        holder.itemPrice.text = dishes[position].prices[0].price + "€"


        val data = dishes[position]
        holder.itemView.setOnClickListener{
            cellClickListener.onCellClickListener(data)
        }
    }

    override fun getItemCount(): Int = dishes.size
}