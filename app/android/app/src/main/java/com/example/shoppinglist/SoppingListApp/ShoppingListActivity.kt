package com.example.shoppinglist.SoppingListApp

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.shoppinglist.R
import com.example.shoppinglist.RegisterLogin.RegisterLogin
import com.example.shoppinglist.databinding.ActivityShoppingListBinding
import com.google.firebase.auth.FirebaseAuth

class ShoppingListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShoppingListBinding
    private var TAG: String = "LogsShoppingListActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityShoppingListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_shopping_list)
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
        // super.onCreate(savedInstanceState, persistentState)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping_list)
        val navHostFragment = findNavController(R.id.mobile_navigation)
        // val navHostFragment = supportFragmentManager.findFragmentById(R.id.mobile_navigation) as NavHostFragment
        //NavigationUI.setupActionBarWithNavController(this, navHostFragment.navController)

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.mobile_navigation)
        return navController.navigateUp()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){

            R.id.action_about -> { }
            R.id.action_signout -> SignOut()

        }
        return super.onOptionsItemSelected(item)
    }

    private fun SignOut(){
        FirebaseAuth.getInstance().signOut()
        val intent = Intent(this, RegisterLogin::class.java).apply {  }
        startActivity(intent)
        finish()
    }

}