package com.example.shoppinglist.RegisterLogin.ui

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


    val register: MutableLiveData<registerStatus> by lazy {
        MutableLiveData<registerStatus>()
    }

    val login: MutableLiveData<loginStatus> by lazy{
        MutableLiveData<loginStatus>()
    }

    fun SignUp(credentials: credentials, activity: FragmentActivity?){

        auth = Firebase.auth
        activity?.let { activity ->
            auth.createUserWithEmailAndPassword(credentials.email, credentials.password)
                .addOnCompleteListener(activity) { task ->

                    if (task.isSuccessful){
                        Log.i(TAG, "Successfully Singed Up: Token -> ${task.result.user?.uid}")

                        lateinit var tempRegister: registerStatus
                        FirebaseAuth
                            .getInstance()
                            .currentUser
                            ?.sendEmailVerification()
                            ?.addOnCompleteListener(){ task ->
                                if (task.isSuccessful){
                                    tempRegister = registerStatus(true,"Email sent.")
                                    register.value = tempRegister
                                    Log.i(TAG, "Verification email sent Successfully.!")
                                } else {
                                    var splitedDetail = task.exception.toString()
                                    var splitedDetailArr = splitedDetail.split(":")
                                    tempRegister = registerStatus(false,splitedDetailArr.get(1))
                                    register.value = tempRegister
                                    Log.i(TAG, "Fail to verification email.!")
                                }
                            }

                    } else {
                        Log.i(TAG, "Error Singed Up: ${task.exception}")
                        var splitedDetail = task.exception.toString()
                        var splitedDetailArr = splitedDetail.split(":")
                        var tempRegister = registerStatus(false,splitedDetailArr.get(1))
                        register.value = tempRegister
                    }

                }
        }
    }

    fun SignIn(credentials: credentials, activity: FragmentActivity?){

        auth = Firebase.auth
        activity?.let {
            auth.signInWithEmailAndPassword(credentials.email, credentials.password)
                .addOnCompleteListener(it){ task ->
                    if (task.isSuccessful){
                        var user = FirebaseAuth.getInstance().currentUser
                        if (user != null) {
                            lateinit var tempLoginStatus: loginStatus
                            var tempUser = user(
                                auth.currentUser?.displayName.toString(),
                                auth.currentUser?.email.toString(),
                                auth.currentUser?.uid.toString()
                            )
                            if (user.isEmailVerified){
                                Log.i(TAG, "Successfully Logged In:")
                                tempLoginStatus = loginStatus(
                                    true,
                                    task.result.toString(),
                                    tempUser
                                )
                            } else {
                                tempLoginStatus = loginStatus(
                                    false,
                                    "Unverified email",
                                    tempUser
                                )
                            }
                            login.value = tempLoginStatus
                        }

                    } else {
                        Log.i(TAG, "Error Singed Up: ${task.exception}")
                        var tempUser = user(
                            null,
                            null,
                            null
                        )
                        var splitedDetail = task.exception.toString()
                        var splitedDetailArr = splitedDetail.split(":")
                        var tempLoginStatus = loginStatus(
                            false,
                            splitedDetailArr.get(1),
                            tempUser
                        )
                        login.value = tempLoginStatus
                    }
                }
        }
    }

}