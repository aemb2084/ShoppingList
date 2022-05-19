package com.example.shoppinglist.shoppingListApp.ui.products.createProduct

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.shoppinglist.R
import com.example.shoppinglist.databinding.FragmentCreateProductBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CreateProductFragment : Fragment() {

    var TAG: String = "CreateProductFragmentLogs"
    val viewModel by viewModels<CreateProductViewModel>()
    private var _binding: FragmentCreateProductBinding? = null
    private val binding get() = _binding!!
    private var navController: NavController? = null
    private var categoryList: List<String> = emptyList()
    private var providersList: List<String> = emptyList()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        _binding = FragmentCreateProductBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val db = Firebase.firestore
        val categoryOptions: AutoCompleteTextView = binding.categoryMenu
        categoryList = listOf("1","2","3")
        val categoriesAdapter = ArrayAdapter(requireContext(), R.layout.list, categoryList)
        categoryOptions.setAdapter(categoriesAdapter)

        db.collection("menu_options").document("options")
            .get()
            .addOnSuccessListener { result ->
                categoryList = result.data?.get("categories") as List<String>
                Log.i(TAG,"Categories : $categoryList")

            }
        db.collection("menu_options").document("options")
            .get()
            .addOnSuccessListener { result ->
                providersList = result.data?.get("providers") as List<String>
                Log.i(TAG,"Providers : $providersList")
            }


        /*
        Código para llenar los dropdown menus

        val items = listOf("Option 1", "Option 2", "Option 3", "Option 4")
        val adapter = ArrayAdapter(requireContext(), R.layout.list_item, items)
        (textField.editText as? AutoCompleteTextView)?.setAdapter(adapter)
         */

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