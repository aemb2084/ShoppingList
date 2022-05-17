package com.example.shoppinglist.shoppingListApp.ui.historical

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.shoppinglist.shoppingListApp.ui.cart.CartViewModel
import com.example.shoppinglist.databinding.FragmentHistoricalBinding

class HistoricalFragment : Fragment() {

    private var _binding: FragmentHistoricalBinding? = null
    val viewModel by viewModels<CartViewModel>()
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        _binding = FragmentHistoricalBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHistorical
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