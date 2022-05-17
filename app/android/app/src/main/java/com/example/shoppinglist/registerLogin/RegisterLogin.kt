package com.example.shoppinglist.registerLogin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.shoppinglist.R
import com.example.shoppinglist.shoppingListApp.ShoppingListActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterLogin : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_login_activity)
    }

    override fun onStart() {
        super.onStart()

        auth = Firebase.auth

        if (auth.currentUser != null){
            val intent = Intent(this, ShoppingListActivity::class.java)
            startActivity(intent)
        }


    }
}