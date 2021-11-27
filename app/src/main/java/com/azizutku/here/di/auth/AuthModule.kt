package com.azizutku.here.di.auth

import com.azizutku.here.data.repository.auth.AuthRepositoryImpl
import com.azizutku.here.data.repository.auth.datasource.AuthCacheDataSource
import com.azizutku.here.data.repository.auth.datasource.AuthRemoteDataSource
import com.azizutku.here.data.repository.auth.datasourceImpl.AuthCacheDataSourceImpl
import com.azizutku.here.data.repository.auth.datasourceImpl.AuthRemoteDataSourceImpl
import com.azizutku.here.domain.repository.auth.AuthRepository
import com.azizutku.here.domain.usecase.auth.GetUserUseCase
import com.azizutku.here.domain.usecase.auth.LoginUseCase
import com.azizutku.here.domain.usecase.auth.SignupUseCase
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AuthModule {
    @Singleton
    @Provides
    fun provideAuthUserRepository(
        cacheDataSource: AuthCacheDataSource,
        remoteDataSource: AuthRemoteDataSource,
        firebaseAuth: FirebaseAuth,
    ): AuthRepository {
        return AuthRepositoryImpl(cacheDataSource, remoteDataSource, firebaseAuth)
    }

    @Singleton
    @Provides
    fun provideAuthCacheDataSource(): AuthCacheDataSource {
        return AuthCacheDataSourceImpl()
    }

    @Singleton
    @Provides
    fun provideAuthRemoteDataSource(): AuthRemoteDataSource {
        return AuthRemoteDataSourceImpl()
    }

    @Singleton
    @Provides
    fun provideLoginUseCase(repository: AuthRepository): LoginUseCase {
        return LoginUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideGetUserUseCase(repository: AuthRepository): GetUserUseCase {
        return GetUserUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideSignupUseCase(repository: AuthRepository): SignupUseCase {
        return SignupUseCase(repository)
    }

}
