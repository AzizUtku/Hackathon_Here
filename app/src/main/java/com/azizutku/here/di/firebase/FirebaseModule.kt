package com.azizutku.here.di.firebase

import com.azizutku.here.data.repository.auth.AuthRepositoryImpl
import com.azizutku.here.data.repository.auth.datasource.AuthCacheDataSource
import com.azizutku.here.data.repository.auth.datasource.AuthRemoteDataSource
import com.azizutku.here.data.repository.auth.datasourceImpl.AuthCacheDataSourceImpl
import com.azizutku.here.data.repository.auth.datasourceImpl.AuthRemoteDataSourceImpl
import com.azizutku.here.domain.repository.auth.AuthRepository
import com.azizutku.here.domain.usecase.auth.LoginUseCase
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object FirebaseModule {

    @Singleton
    @Provides
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

}
