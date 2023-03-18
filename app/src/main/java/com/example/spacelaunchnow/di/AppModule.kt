package com.example.spacelaunchnow.di

import com.example.spacelaunchnow.common.Constants.BASE_URL
import com.example.spacelaunchnow.data.remote.SpaceLaunchAPI
import com.example.spacelaunchnow.data.repository.AstronautRepositoryImpl
import com.example.spacelaunchnow.domain.repository.AstronautRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideSpaceLaunchAPI(): SpaceLaunchAPI {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(SpaceLaunchAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideAstronautRepository(api: SpaceLaunchAPI): AstronautRepository {
        return AstronautRepositoryImpl(api)
    }
}