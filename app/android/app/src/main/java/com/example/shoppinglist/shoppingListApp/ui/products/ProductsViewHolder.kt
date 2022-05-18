package com.example.shoppinglist.shoppingListApp.ui.products

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.R

class ProductsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val productImage = view.findViewById(R.id.ProductImage) as ImageView
    val productTitle = view.findViewById(R.id.ProductTitle) as TextView
    val productCategory = view.findViewById(R.id.ProductCategory) as TextView
    val productProvider = view.findViewById(R.id.ProductProvider) as TextView
    val productPrice = view.findViewById(R.id.ProductPrice) as TextView
    val scoreStar1 = view.findViewById(R.id.ProductQualityStar1) as ImageView
    val scoreStar2 = view.findViewById(R.id.ProductQualityStar2) as ImageView
    val scoreStar3 = view.findViewById(R.id.ProductQualityStar3) as ImageView
    val scoreStar4 = view.findViewById(R.id.ProductQualityStar4) as ImageView
    val scoreStar5 = view.findViewById(R.id.ProductQualityStar5) as ImageView
    val productObservation = view.findViewById(R.id.ProductObservations) as TextView

    fun bind (product: ProductsModel, context: Context){
        productTitle.text = product.title
    }

}