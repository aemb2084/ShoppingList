package com.example.shoppinglist.registerLogin.ui

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class Credentials (var email: String, var password: String)

@Parcelize
data class User (
    var Name: String?,
    var Email: String?,
    var uid: String?
    ): Parcelable

data class RegisterStatus(var status: Boolean, var detail: String)

data class LoginStatus(var status: Boolean, var detail: String, var user: User)