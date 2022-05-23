package com.example.shoppinglist.shoppingListApp.ui.products.createProduct

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoppinglist.shoppingListApp.ui.products.ProductMenus
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CreateProductViewModel : ViewModel() {

    val db = Firebase.firestore
    var menus: MutableLiveData<ProductMenus> = MutableLiveData()
    var rate: MutableLiveData<Int> = MutableLiveData()

    fun Init(){
        db.collection("menu_options").document("options")
            .get()
            .addOnSuccessListener { result ->

                var categoryList = result.data?.get("categories") as List<String>
                var providersList = result.data?.get("providers") as List<String>

                var tempMenus = ProductMenus(
                    categoryList,
                    providersList
                )
                menus.value = tempMenus
            }
    }

    fun Rate(Rate: Int){
        rate.value = Rate
    }

}