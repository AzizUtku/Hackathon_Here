package com.azizutku.here.data.repository.auth.datasourceImpl

import com.azizutku.here.data.repository.auth.datasource.AuthCacheDataSource
import com.google.firebase.auth.AuthResult

class AuthCacheDataSourceImpl : AuthCacheDataSource {

    private var user: AuthResult? = null

    override suspend fun getUser(): AuthResult? {
        return user
    }

    override suspend fun saveUser(user: AuthResult) {
        this.user = user
    }

}
