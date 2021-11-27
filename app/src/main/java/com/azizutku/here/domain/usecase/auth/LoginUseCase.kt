package com.azizutku.here.domain.usecase.auth

import com.azizutku.here.domain.repository.auth.AuthRepository
import com.azizutku.here.vo.DataState
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val repository: AuthRepository) {

    suspend fun execute(email: String, password: String): Flow<DataState<AuthResult>>
        = repository.login(email, password)

}
