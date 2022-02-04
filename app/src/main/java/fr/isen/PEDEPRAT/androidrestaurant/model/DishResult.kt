package fr.isen.PEDEPRAT.androidrestaurant.model

import java.io.Serializable

data class DishResult(var data: List<Category>) : Serializable//constructeur : fonction qui lui permet de renvoyer instance de cet obj

data class Category (val name_fr : String, val items:List<DishModel>) : Serializable

data class DishModel(val name_fr : String, val images : List<String>, val prices : List<PriceModel>, val ingredients: List<Ingredient>):Serializable {
    fun getFirstPicture() = if (images.isNotEmpty() && images[0].isNotEmpty()) {
        images[0]
    } else {
        null
    }
    fun get_all_pictures() = if (images.isNotEmpty() && images.any { it.isNotEmpty()}){
        images.filter {it.isNotEmpty()}
        }
    else{
        null
    }
}

data class PriceModel(val price : String): Serializable

data class Ingredient(val name_fr: String): Serializable

data class Panier(val items : MutableList<ItemPanier>) : Serializable

data class ItemPanier(val quantity : Int , val dish : DishModel) : Serializable