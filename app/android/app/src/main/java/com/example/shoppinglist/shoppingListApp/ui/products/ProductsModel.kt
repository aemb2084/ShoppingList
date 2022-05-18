package com.example.shoppinglist.shoppingListApp.ui.products

data class ProductsModel(

    val pictureUrl: String,
    val title: String,
    val category: String,
    val provider: String,
    val price: Int,
    val qualification: Int,
    val observations: String

)
