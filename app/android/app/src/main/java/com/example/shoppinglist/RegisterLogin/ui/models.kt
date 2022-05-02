package com.example.shoppinglist.RegisterLogin.ui

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class credentials (var email: String, var password: String)

@Parcelize
data class user (
    var name: String?,
    var email: String?,
    var uid: String?
    ): Parcelable

data class registerStatus(var status: Boolean, var detail: String)

data class loginStatus(var status: Boolean, var detail: String, var user: user)