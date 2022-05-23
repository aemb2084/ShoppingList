package com.example.shoppinglist.shoppingListApp.ui.products.createProduct

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.shoppinglist.R
import com.example.shoppinglist.databinding.FragmentCreateProductBinding
import com.example.shoppinglist.shoppingListApp.ui.products.ProductMenus
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CreateProductFragment : Fragment(), View.OnClickListener {

    var TAG: String = "CreateProductFragmentLogs"
    val viewModel by viewModels<CreateProductViewModel>()
    private var _binding: FragmentCreateProductBinding? = null
    private val binding get() = _binding!!
    private var navController: NavController? = null
    private var categoryList: List<String> = emptyList()
    private var providersList: List<String> = emptyList()
    private lateinit var progressBar: ProgressBar
    private lateinit var categoryOptions: AutoCompleteTextView
    private lateinit var providerOptions: AutoCompleteTextView
    private lateinit var generalLayout: ConstraintLayout
    private lateinit var rateStar1: ImageView
    private lateinit var rateStar2: ImageView
    private lateinit var rateStar3: ImageView
    private lateinit var rateStar4: ImageView
    private lateinit var rateStar5: ImageView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        _binding = FragmentCreateProductBinding.inflate(inflater, container, false)
        val root: View = binding.root

        progressBar = binding.progressBar
        categoryOptions = binding.categoryMenu
        providerOptions = binding.providerMenu
        generalLayout = binding.generalLayout

        rateStar1 = binding.RatingStar1
        rateStar1.setOnClickListener(this)
        rateStar2 = binding.RatingStar2
        rateStar2.setOnClickListener(this)
        rateStar3 = binding.RatingStar3
        rateStar3.setOnClickListener(this)
        rateStar4 = binding.RatingStar4
        rateStar4.setOnClickListener(this)
        rateStar5 = binding.RatingStar5
        rateStar5.setOnClickListener(this)

        progressBar.isVisible = true
        generalLayout.isVisible = false

        return root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.Init()

        viewModel.menus.observe(this){ menus ->
            StartCreatingProduct(menus)
        }

        viewModel.rate.observe(this){ rate ->
            Rate(rate)
        }

    }

    fun StartCreatingProduct(menus: ProductMenus){

        generalLayout.isVisible = true
        progressBar.isVisible = false

        categoryList = menus.categoryList
        val categoriesAdapter = ArrayAdapter(requireContext(), R.layout.list, categoryList)
        categoryOptions.setAdapter(categoriesAdapter)
        providersList = menus.providerList
        val providerAdapter = ArrayAdapter(requireContext(), R.layout.list, providersList)
        providerOptions.setAdapter(providerAdapter)

    }

    override fun onClick(v: View?) {

        when(v?.id){
            R.id.RatingStar1 -> viewModel.Rate(1)
            R.id.RatingStar2 -> viewModel.Rate(2)
            R.id.RatingStar3 -> viewModel.Rate(3)
            R.id.RatingStar4 -> viewModel.Rate(4)
            R.id.RatingStar5 -> viewModel.Rate(5)
        }

    }

    fun Rate(rate: Int){
       when(rate){
           1 -> {
               rateStar1.setImageResource(R.drawable.ic_baseline_star_24)
               rateStar2.setImageResource(R.drawable.ic_baseline_star_border_24)
               rateStar3.setImageResource(R.drawable.ic_baseline_star_border_24)
               rateStar4.setImageResource(R.drawable.ic_baseline_star_border_24)
               rateStar5.setImageResource(R.drawable.ic_baseline_star_border_24)
           }
           2 -> {
               rateStar1.setImageResource(R.drawable.ic_baseline_star_24)
               rateStar2.setImageResource(R.drawable.ic_baseline_star_24)
               rateStar3.setImageResource(R.drawable.ic_baseline_star_border_24)
               rateStar4.setImageResource(R.drawable.ic_baseline_star_border_24)
               rateStar5.setImageResource(R.drawable.ic_baseline_star_border_24)
           }
           3 -> {
               rateStar1.setImageResource(R.drawable.ic_baseline_star_24)
               rateStar2.setImageResource(R.drawable.ic_baseline_star_24)
               rateStar3.setImageResource(R.drawable.ic_baseline_star_24)
               rateStar4.setImageResource(R.drawable.ic_baseline_star_border_24)
               rateStar5.setImageResource(R.drawable.ic_baseline_star_border_24)
           }
           4 -> {
               rateStar1.setImageResource(R.drawable.ic_baseline_star_24)
               rateStar2.setImageResource(R.drawable.ic_baseline_star_24)
               rateStar3.setImageResource(R.drawable.ic_baseline_star_24)
               rateStar4.setImageResource(R.drawable.ic_baseline_star_24)
               rateStar5.setImageResource(R.drawable.ic_baseline_star_border_24)
           }
           5 -> {
               rateStar1.setImageResource(R.drawable.ic_baseline_star_24)
               rateStar2.setImageResource(R.drawable.ic_baseline_star_24)
               rateStar3.setImageResource(R.drawable.ic_baseline_star_24)
               rateStar4.setImageResource(R.drawable.ic_baseline_star_24)
               rateStar5.setImageResource(R.drawable.ic_baseline_star_24)
           }
       }
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