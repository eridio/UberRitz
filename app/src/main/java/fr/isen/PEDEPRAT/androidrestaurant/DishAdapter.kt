package fr.isen.PEDEPRAT.androidrestaurant

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.isen.PEDEPRAT.androidrestaurant.databinding.CardViewDesignBinding
import fr.isen.PEDEPRAT.androidrestaurant.model.Dish
import fr.isen.PEDEPRAT.androidrestaurant.model.DishModel
import fr.isen.PEDEPRAT.androidrestaurant.model.DishResult

class DishAdapter(val dishes : List<DishModel>, val onDishClick:(DishModel)-> Unit): RecyclerView.Adapter<DishAdapter.DishViewHolder>() {

    class DishViewHolder(val binding: CardViewDesignBinding):RecyclerView.ViewHolder(binding.root){
        val itemimage = binding.itemimage
        val itemName = binding.itemName
        val itemPrice = binding.itemPrice
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishViewHolder {

            val binding = CardViewDesignBinding.inflate((LayoutInflater.from(parent.context)),parent,false)
            return DishViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DishViewHolder, position: Int) {

        val dish = dishes[position]

        holder.itemName.text = dish.name_fr
       // holder.itemimage.setImageResource(dishes[position].image)
        //holder.itemPrice.text = dishes[position].Price

        holder.itemView.setOnClickListener{
            onDishClick(dishes[position])
        }
    }

    override fun getItemCount(): Int = dishes.size
}