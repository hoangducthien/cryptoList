package com.example.data.di

import android.content.Context
import androidx.room.Room
import com.example.data.common.AppDataBase
import com.example.data.currency.crypto.CryptoRepositoryImpl
import com.example.data.currency.crypto.CryptoService
import com.example.data.currency.fiat.FiatRepositoryImpl
import com.example.data.currency.fiat.FiatService
import com.example.domain.currency.crypto.CryptoRepository
import com.example.domain.currency.fiat.FiatRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModules {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDataBase {
        return Room.databaseBuilder(
            context,
            AppDataBase::class.java, "photos-homework-db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideFiatRepository(fiatService: FiatService, appDataBase: AppDataBase, ): FiatRepository {
        return FiatRepositoryImpl(fiatService, appDataBase, Dispatchers.IO)
    }

    @Provides
    @Singleton
    fun provideCryptoRepository(cryptoService: CryptoService, appDataBase: AppDataBase): CryptoRepository {
        return CryptoRepositoryImpl(cryptoService, appDataBase, Dispatchers.IO)
    }
}