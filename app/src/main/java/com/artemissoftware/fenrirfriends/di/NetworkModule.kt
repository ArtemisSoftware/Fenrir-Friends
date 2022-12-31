package com.artemissoftware.fenrirfriends.di

import android.os.Build
import com.artemissoftware.data.remote.DogApi
import com.artemissoftware.data.remote.source.DogApiSource
import com.artemissoftware.data.remote.source.DogApiSourceImpl
import com.artemissoftware.fenrirfriends.BuildConfig.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @Singleton
    @Provides
    fun provideHttpClient() : OkHttpClient {

        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .build()
    }


    @Provides
    @Singleton
    fun provideDogApi(okHttpClient: OkHttpClient): DogApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DogApi::class.java)
    }


    @Provides
    @Singleton
    fun provideDogApiSource(dogApi: DogApi): DogApiSource {
        return DogApiSourceImpl(dogApi)
    }
}