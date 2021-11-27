package com.azizutku.here.domain.usecase.auth

import com.azizutku.here.domain.repository.auth.AuthRepository
import com.azizutku.here.vo.DataState
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserUseCase @Inject constructor(private val repository: AuthRepository) {

    suspend fun execute(): Flow<DataState<AuthResult>> = repository.getUser()

}
