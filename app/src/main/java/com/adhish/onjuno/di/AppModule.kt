package com.adhish.onjuno.di

import android.util.Log
import com.adhish.onjuno.network.NetworkCall
import com.adhish.onjuno.util.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private const val TAG = "AppModule"

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    //To inject okhttp instance
    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient().newBuilder().addInterceptor { chain ->
            val request = chain.request()
            Log.d(TAG, "provideHttpClient: Netwrok Request = $request")
            chain.proceed(request)
        }.build()
    }

    //To inject retrofit instance in repository
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): NetworkCall = Retrofit
        .Builder()
        .baseUrl(Constant.BASE_URl)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(NetworkCall::class.java)
}