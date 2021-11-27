package com.azizutku.here.data.repository.auth.datasourceImpl

import com.azizutku.here.data.model.User
import com.azizutku.here.data.repository.auth.datasource.AuthCacheDataSource
import com.google.firebase.auth.AuthResult

class AuthCacheDataSourceImpl : AuthCacheDataSource {

    private var authResult: AuthResult? = null
    private var user: User? = null

    override suspend fun getAuthResult(): AuthResult? {
        return authResult
    }

    override suspend fun saveAuthResult(authResult: AuthResult) {
        this.authResult = authResult
    }

    override suspend fun getUser(): User? {
        return user
    }

    override suspend fun saveUser(user: User) {
        this.user = user
    }

}
