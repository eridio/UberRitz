package fr.isen.PEDEPRAT.androidrestaurant

import fr.isen.PEDEPRAT.androidrestaurant.model.DishModel

interface  CellClickListener {

    fun onCellClickListener(data : DishModel)
}