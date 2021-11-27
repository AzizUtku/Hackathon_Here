package com.azizutku.here.data.repository.auth.datasource

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

interface AuthRemoteDataSource {

    suspend fun login(firebaseAuth: FirebaseAuth, email:String, password:String): AuthResult

}
