package com.example.premierleagueapp.di

import com.example.premierleagueapp.data.api.ApiService
import com.example.premierleagueapp.data.api.MatchApi
import com.example.premierleagueapp.data.repository.MatchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideMatchApi(): MatchApi {
        return ApiService.matchApi
    }

    @Provides
    @Singleton
    fun provideMatchRepository(matchApi: MatchApi): MatchRepository {
        return MatchRepository(matchApi)
    }
}