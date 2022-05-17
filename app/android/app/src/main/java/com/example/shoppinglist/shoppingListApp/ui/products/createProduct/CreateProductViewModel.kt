package com.example.shoppinglist.shoppingListApp.ui.products.createProduct

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CreateProductViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Create Product Fragment"
    }
    val text: LiveData<String> = _text

}