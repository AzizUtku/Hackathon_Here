package com.azizutku.here.data.repository.auth.datasource

import com.azizutku.here.data.model.User
import com.google.firebase.auth.AuthResult

interface AuthCacheDataSource {

    suspend fun getAuthResult(): AuthResult?
    suspend fun saveAuthResult(authResult: AuthResult)

    suspend fun getUser(): User?
    suspend fun saveUser(user: User)

}
