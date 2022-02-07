package fr.isen.PEDEPRAT.androidrestaurant.model

data class RegisterResult(val data : User, val code: Int)

data class User(val id: String)
