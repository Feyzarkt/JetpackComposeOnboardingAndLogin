package com.myapps.jetpackcomposeproject.di

import com.huawei.agconnect.auth.AGConnectAuth
import com.huawei.agconnect.auth.VerifyCodeSettings
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AuthServiceModule {

    @Provides
    @Singleton
    fun provideAGConnectAuth(): AGConnectAuth {
        return AGConnectAuth.getInstance()
    }

    @Provides
    @Singleton
    fun provideCodeSettings(): VerifyCodeSettings {
        return VerifyCodeSettings.newBuilder()
            .action(VerifyCodeSettings.ACTION_REGISTER_LOGIN)
            .sendInterval(30)
            .locale(Locale.ENGLISH)
            .build()
    }
}