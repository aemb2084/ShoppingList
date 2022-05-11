package com.example.shoppinglist.SoppingListApp.ui.Products

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import com.example.shoppinglist.databinding.FragmentProductsBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ProductsFragment : Fragment() {

    val TAG: String = "LogsProductsFragment"
    val viewModel by viewModels<ProductsViewModel>()
    private var _binding: FragmentProductsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _binding = FragmentProductsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val addProduct: FloatingActionButton = binding.addProduct
        addProduct.setOnClickListener{
            Log.i(TAG, "FAB")
        }

        val textView: TextView = binding.textProducts
        viewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}