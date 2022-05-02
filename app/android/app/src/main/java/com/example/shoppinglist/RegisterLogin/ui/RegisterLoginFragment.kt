package com.example.shoppinglist.RegisterLogin.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.shoppinglist.R
import com.example.shoppinglist.SoppingListApp.ShoppingListActivity
import com.example.shoppinglist.databinding.RegisterLoginFragmentBinding
import com.google.firebase.auth.FirebaseAuth

class RegisterLoginFragment : Fragment(), View.OnClickListener {

    private var TAG: String = "LogLoginFragment"
    val viewModel: RegisterLoginViewModel by viewModels()
    private var _binding: RegisterLoginFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = RegisterLoginFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(false)
        viewModel.register.observe(this, Observer<registerStatus>{ register ->
            registerFeedback(register)
        })
        viewModel.login.observe(this, Observer<loginStatus>{ login ->
            loginFeedback(login)
        })

    }
    override fun onClick(v: View?) {

        val myUser = credentials(
            binding.email.text.toString(),
            binding.password.text.toString()
        )

        when(v?.id){
            R.id.login -> viewModel.SignIn(myUser, getActivity())
            R.id.signup -> viewModel.SignUp(myUser, getActivity())
        }

    }

    private fun registerFeedback(register: registerStatus){

        if (register.status){
            Toast.makeText(context,getString(R.string.ConfirmEmail), Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(context,"Error: ${register.detail}", Toast.LENGTH_LONG).show()
        }
    }

    private fun loginFeedback(login: loginStatus){
        if (login.status){
            Toast.makeText(context, getString(R.string.LoginSuccess), Toast.LENGTH_LONG).show()
            val bundle = bundleOf("user" to login.user)
            // navController!!.navigate(R.id.action_loginFragment_to_mainFragment, bundle)
            requireActivity().run {
                startActivity(Intent(this, ShoppingListActivity::class.java))
                finish()
            }
            // TODO -> Gestionar el cambio de actividad.
        } else {
            Toast.makeText(context, "Error: ${login.detail}", Toast.LENGTH_LONG).show()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.login.setOnClickListener (this)
        binding.signup.setOnClickListener(this)
        // navController = Navigation.findNavController(view)

    }

}