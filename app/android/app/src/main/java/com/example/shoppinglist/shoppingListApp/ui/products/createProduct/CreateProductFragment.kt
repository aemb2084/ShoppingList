package com.example.shoppinglist.shoppingListApp.ui.products.createProduct

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.shoppinglist.databinding.FragmentCreateProductBinding

class CreateProductFragment : Fragment() {

    val viewModel by viewModels<CreateProductViewModel>()
    private var _binding: FragmentCreateProductBinding? = null
    private val binding get() = _binding!!
    private var navController: NavController? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        _binding = FragmentCreateProductBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textCreateProduct
        viewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}