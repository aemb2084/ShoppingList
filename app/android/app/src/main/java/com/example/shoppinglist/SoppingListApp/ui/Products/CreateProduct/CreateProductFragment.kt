package com.example.shoppinglist.SoppingListApp.ui.Products.CreateProduct

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import com.example.shoppinglist.R
import com.example.shoppinglist.SoppingListApp.ui.Products.ProductsViewModel
import com.example.shoppinglist.databinding.FragmentCreateProductBinding
import com.example.shoppinglist.databinding.FragmentProductsBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class CreateProductFragment : Fragment() {

    val viewModel by viewModels<CreateProductViewModel>()
    private var _binding: FragmentCreateProductBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _binding = FragmentCreateProductBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textCreateProduct
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