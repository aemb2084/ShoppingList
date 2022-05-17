package com.example.shoppinglist.registerLogin.ui

import android.util.Log
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterLoginViewModel : ViewModel() {

    private var TAG: String = "LogLoginFragmentViewModel"
    private lateinit var auth: FirebaseAuth


    val register: MutableLiveData<RegisterStatus> by lazy {
        MutableLiveData<RegisterStatus>()
    }

    val login: MutableLiveData<LoginStatus> by lazy{
        MutableLiveData<LoginStatus>()
    }

    fun signUp(credentials: Credentials, activity: FragmentActivity?){

        auth = Firebase.auth
        activity?.let { myActivity ->
            auth.createUserWithEmailAndPassword(credentials.email, credentials.password)
                .addOnCompleteListener(myActivity) { task ->

                    if (task.isSuccessful){
                        Log.i(TAG, "Successfully Singed Up: Token -> ${task.result.user?.uid}")

                        lateinit var tempRegister: RegisterStatus
                        FirebaseAuth
                            .getInstance()
                            .currentUser
                            ?.sendEmailVerification()
                            ?.addOnCompleteListener { myTask ->
                                if (myTask.isSuccessful){
                                    tempRegister = RegisterStatus(true,"Email sent.")
                                    register.value = tempRegister
                                    Log.i(TAG, "Verification email sent Successfully.!")
                                } else {
                                    val splitDetail = myTask.exception.toString()
                                    val splitDetailArr = splitDetail.split(":")
                                    tempRegister = RegisterStatus(false, splitDetailArr[1])
                                    register.value = tempRegister
                                    Log.i(TAG, "Fail to verification email.!")
                                }
                            }

                    } else {
                        Log.i(TAG, "Error Singed Up: ${task.exception}")
                        val splitDetail = task.exception.toString()
                        val splitDetailArr = splitDetail.split(":")
                        val tempRegister = RegisterStatus(false, splitDetailArr[1])
                        register.value = tempRegister
                    }

                }
        }
    }

    fun signIn(credentials: Credentials, activity: FragmentActivity?){

        auth = Firebase.auth
        activity?.let {
            auth.signInWithEmailAndPassword(credentials.email, credentials.password)
                .addOnCompleteListener(it){ task ->
                    if (task.isSuccessful){
                        val user = FirebaseAuth.getInstance().currentUser
                        if (user != null) {
                            lateinit var tempLoginStatus: LoginStatus
                            val tempUser = User(
                                auth.currentUser?.displayName.toString(),
                                auth.currentUser?.email.toString(),
                                auth.currentUser?.uid.toString()
                            )
                            tempLoginStatus = if (user.isEmailVerified){
                                Log.i(TAG, "Successfully Logged In:")
                                LoginStatus(
                                    true,
                                    task.result.toString(),
                                    tempUser
                                )
                            } else {
                                LoginStatus(
                                    false,
                                    "Unverified email",
                                    tempUser
                                )
                            }
                            login.value = tempLoginStatus
                        }

                    } else {
                        Log.i(TAG, "Error Singed Up: ${task.exception}")
                        val tempUser = User(
                            null,
                            null,
                            null
                        )
                        val splitDetail = task.exception.toString()
                        val splitDetailArr = splitDetail.split(":")
                        val tempLoginStatus = LoginStatus(
                            false,
                            splitDetailArr[1],
                            tempUser
                        )
                        login.value = tempLoginStatus
                    }
                }
        }
    }

}