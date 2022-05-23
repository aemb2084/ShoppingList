package com.example.shoppinglist.shoppingListApp.ui.products

data class ProductsModels(

    val pictureUrl: String,
    val title: String,
    val category: String,
    val provider: String,
    val price: Int,
    val qualification: Int,
    val observations: String

)

data class ProductMenus(
    val categoryList: List<String>,
    val providerList: List<String>
)
