package com.myapps.jetpackcomposeproject.ui.home

import androidx.lifecycle.ViewModel
import com.huawei.agconnect.auth.AGConnectAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val auth: AGConnectAuth
): ViewModel(){

}