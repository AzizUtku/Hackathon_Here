package com.azizutku.here.data.repository.auth

import com.azizutku.here.data.model.User
import com.azizutku.here.data.repository.auth.datasource.AuthCacheDataSource
import com.azizutku.here.data.repository.auth.datasource.AuthRemoteDataSource
import com.azizutku.here.domain.repository.auth.AuthRepository
import com.azizutku.here.vo.DataState
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject

class AuthRepositoryImpl
@Inject constructor(
    private val cacheDataSource: AuthCacheDataSource,
    private val remoteDataSource: AuthRemoteDataSource,
    private val firebaseAuth: FirebaseAuth,
) : AuthRepository {

    override suspend fun getUser(): Flow<DataState<AuthResult>> = flow {
        emit(DataState.Loading)
        val user = cacheDataSource.getAuthResult()
        if (user != null) {
            Timber.i("User fetched from cache: $user")
            emit(DataState.Success(user))
        } else {
            Timber.i("User cannot fetched from cache")
            emit(DataState.Error(Throwable("User cannot fetched from cache")))
        }
    }

    override suspend fun login(email: String, password: String): Flow<DataState<AuthResult>> = flow {
        emit(DataState.Loading)
        delay(1200)
        runCatching {
            val authResult = remoteDataSource.login(firebaseAuth, email, password)
            cacheDataSource.saveAuthResult(authResult)
            emit(DataState.Success(authResult))
        }.onFailure {
            Timber.i(it)
            emit(DataState.Error(it))
        }
    }

    override suspend fun signup(): Flow<DataState<User>> = flow {
        emit(DataState.Loading)
        runCatching {
            val user = remoteDataSource.signup()
            cacheDataSource.saveUser(user)
            emit(DataState.Success(user))
        }.onFailure {
            Timber.i(it)
            emit(DataState.Error(it))
        }
    }

    override suspend fun logout(): Flow<DataState<Nothing>> = flow {
        // TODO: Implement method
    }

}
