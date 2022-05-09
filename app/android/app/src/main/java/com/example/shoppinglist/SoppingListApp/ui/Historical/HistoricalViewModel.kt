package com.example.shoppinglist.SoppingListApp.ui.Historical

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HistoricalViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Historical Fragment"
    }
    val text: LiveData<String> = _text
}