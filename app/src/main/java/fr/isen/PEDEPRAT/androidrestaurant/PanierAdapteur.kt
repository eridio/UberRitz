package fr.isen.PEDEPRAT.androidrestaurant

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.isen.PEDEPRAT.androidrestaurant.databinding.ActivityPanierAdapteurBinding
import fr.isen.PEDEPRAT.androidrestaurant.model.ItemPanier

public class PanierAdapteur(private val list : MutableList<ItemPanier>, private val delete : (ItemPanier) -> Unit):
    RecyclerView.Adapter<PanierAdapteur.PanierViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PanierAdapteur.PanierViewHolder  {
        context = parent.context
        return PanierViewHolder(ActivityPanierAdapteurBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }



    override fun onBindViewHolder(holder: PanierViewHolder, position: Int) {
        holder.dishName.text = list[position].dish.name_fr
        holder.dishPrice.text = list[position].dish.prices[0].price + "€"
        holder.dishQuantity.text = list[position].quantity.toString()
        Picasso.get().load(list[position].dish.getFirstPicture()).placeholder(R.drawable.ic_launcher_background).into(holder.dishPicture)

        holder.dishDelete.setOnClickListener{
            if(position < list.size) {
                val elementToRemove = list[position]
                delete.invoke(elementToRemove)
                list.remove(elementToRemove)
                //notifyDataSetChanged()
            }
        }
    }
    override fun getItemCount(): Int {
        return list.size
    }

    class PanierViewHolder(categoryBinding: ActivityPanierAdapteurBinding) :
            RecyclerView.ViewHolder(categoryBinding.root) {
                val dishPicture = categoryBinding.dishPicture
                val dishName = categoryBinding.dishName
                val dishPrice = categoryBinding.dishPrice
                val dishQuantity = categoryBinding.quantity
                val dishDelete = categoryBinding.delete
            }
}