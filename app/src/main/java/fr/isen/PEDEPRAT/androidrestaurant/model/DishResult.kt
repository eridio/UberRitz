package fr.isen.PEDEPRAT.androidrestaurant.model

import java.io.Serializable

data class DishResult(var data: List<Category>) : Serializable//constructeur : fonction qui lui permet de renvoyer instance de cet obj

data class Category (val name_fr : String, val items:List<DishModel>) : Serializable

data class DishModel(val name_fr : String, val images : List<String>, val prices : List<PriceModel>):Serializable

data class PriceModel(val price : String): Serializable