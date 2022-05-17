package com.example.shoppinglist.registerLogin.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.shoppinglist.R
import com.example.shoppinglist.shoppingListApp.ShoppingListActivity
import com.example.shoppinglist.databinding.RegisterLoginFragmentBinding

class RegisterLoginFragment : Fragment(), View.OnClickListener {

    // private var TAG: String = "LogLoginFragment"
    val viewModel: RegisterLoginViewModel by viewModels()
    private var _binding: RegisterLoginFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = RegisterLoginFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(false)
        viewModel.register.observe(this) { register ->
            registerFeedback(register)
        }
        viewModel.login.observe(this) { login ->
            loginFeedback(login)
        }

    }
    override fun onClick(v: View?) {

        val myUser = Credentials(
            binding.email.text.toString(),
            binding.password.text.toString()
        )

        when(v?.id){
            R.id.login -> viewModel.signIn(myUser, activity)
            R.id.signup -> viewModel.signUp(myUser, activity)
        }

    }

    private fun registerFeedback(register: RegisterStatus){

        if (register.status){
            Toast.makeText(context,getString(R.string.ConfirmEmail), Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(context,"Error: ${register.detail}", Toast.LENGTH_LONG).show()
        }
    }

    private fun loginFeedback(login: LoginStatus){
        if (login.status){
            Toast.makeText(context, getString(R.string.LoginSuccess), Toast.LENGTH_LONG).show()
            requireActivity().run {
                startActivity(Intent(this, ShoppingListActivity::class.java))
                finish()
            }
        } else {
            Toast.makeText(context, "Error: ${login.detail}", Toast.LENGTH_LONG).show()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.login.setOnClickListener (this)
        binding.signup.setOnClickListener(this)

    }

}