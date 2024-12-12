package com.example.manpaginationroomrecycler.di

import android.content.Context
import com.example.manpaginationroomrecycler.data.db.ResultDatabase
import com.example.manpaginationroomrecycler.data.network.MainAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideAPI(): MainAPI {
        return Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MainAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideDb(@ApplicationContext context: Context): ResultDatabase {
        return ResultDatabase.invoke(context)
    }

}