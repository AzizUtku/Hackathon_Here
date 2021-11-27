package com.azizutku.here.domain.repository.auth

import com.azizutku.here.data.model.User
import com.azizutku.here.vo.DataState
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    suspend fun getUser(): Flow<DataState<AuthResult>>
    suspend fun login(email: String, password: String): Flow<DataState<AuthResult>>
    suspend fun signup(): Flow<DataState<User>>
    suspend fun logout(): Flow<DataState<Nothing>>

}
