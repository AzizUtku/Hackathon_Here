package com.azizutku.here.data.repository.auth.datasourceImpl

import com.azizutku.here.data.model.User
import com.azizutku.here.data.repository.auth.datasource.AuthRemoteDataSource
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay
import kotlinx.coroutines.tasks.await

class AuthRemoteDataSourceImpl: AuthRemoteDataSource {

    override suspend fun login(firebaseAuth: FirebaseAuth, email: String, password: String): AuthResult {
        return firebaseAuth
            .signInWithEmailAndPassword(email, password)
            .await()
    }

    override suspend fun signup(): User {
        delay(1200)
        return User(id = 1)
    }

}
