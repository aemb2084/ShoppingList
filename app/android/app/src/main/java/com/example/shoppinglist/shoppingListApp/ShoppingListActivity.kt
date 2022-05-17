package com.example.shoppinglist.shoppingListApp

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.shoppinglist.R
import com.example.shoppinglist.registerLogin.RegisterLogin
import com.example.shoppinglist.databinding.ActivityShoppingListBinding
import com.google.firebase.auth.FirebaseAuth

class ShoppingListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShoppingListBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
//    private var TAG: String = "LogsShoppingListActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityShoppingListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_shopping_list)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_cart,
                R.id.navigation_list,
                R.id.navigation_historical,
                R.id.navigation_products
            )
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
       menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping_list)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.nav_host_fragment_activity_shopping_list)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){

            R.id.action_about -> { }
            R.id.action_signout -> signOut()

        }
        return super.onOptionsItemSelected(item)
    }

    private fun signOut(){
        FirebaseAuth.getInstance().signOut()
        val intent = Intent(this, RegisterLogin::class.java).apply {  }
        startActivity(intent)
        finish()
    }

}