package com.myapps.jetpackcomposeproject.ui.onboarding

import androidx.lifecycle.ViewModel
import com.huawei.agconnect.auth.AGConnectAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    val auth: AGConnectAuth
): ViewModel(){

}