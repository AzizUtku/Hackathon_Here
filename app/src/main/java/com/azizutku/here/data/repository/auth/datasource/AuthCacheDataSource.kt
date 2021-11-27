package com.azizutku.here.data.repository.auth.datasource

import com.google.firebase.auth.AuthResult

interface AuthCacheDataSource {

    suspend fun getUser(): AuthResult?
    suspend fun saveUser(user: AuthResult)

}
