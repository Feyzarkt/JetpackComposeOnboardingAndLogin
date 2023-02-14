package com.myapps.jetpackcomposeproject.data.repository

import android.util.Log
import com.huawei.agconnect.auth.AGConnectAuth
import com.huawei.agconnect.auth.EmailAuthProvider
import com.huawei.agconnect.auth.EmailUser
import com.huawei.agconnect.auth.VerifyCodeSettings
import com.myapps.jetpackcomposeproject.util.IServiceListener
import javax.inject.Inject

class AuthServiceRepository @Inject constructor(
    private val auth: AGConnectAuth,
    private val settings: VerifyCodeSettings
    ){

    fun verifyEmail(email: String, serviceListener: IServiceListener<Boolean>){
        Log.e("Success", "$settings , $email")
        auth.requestVerifyCode(email, settings).addOnSuccessListener {
            Log.e("Success", "Send Verify Code Your Mail.")
            serviceListener.onSuccess(true)
        }.addOnFailureListener { e ->
            Log.e("Error", "RequestVerifyCode fail:$e")
            serviceListener.onError(e)
        }
    }

    fun registerUser(email: String, password: String, code: String, serviceListener: IServiceListener<Boolean>){
        val emailUser = EmailUser.Builder()
            .setEmail(email)
            .setVerifyCode(code)
            .setPassword(password)
            .build()
        auth.createUser(emailUser).addOnSuccessListener {
            Log.e("Success", "The user registered.")
            serviceListener.onSuccess(true)
        }.addOnFailureListener { e ->
            Log.e("Error", "RegisterUser fail:$e")
            serviceListener.onError(e)
        }
    }

    fun login(email: String, password: String, serviceListener: IServiceListener<Boolean>){
        val credential = EmailAuthProvider.credentialWithPassword(email, password)
        auth.signIn(credential).addOnSuccessListener {
            Log.e("Success", "The user signed in.")
            serviceListener.onSuccess(true)
        }.addOnFailureListener { e ->
            Log.e("Error", "Login fail:$e")
            serviceListener.onError(e)
        }
    }
}