package com.azizutku.here.domain.repository.chat

import com.azizutku.here.data.model.Message
import com.azizutku.here.data.model.Room
import com.azizutku.here.data.model.User
import com.azizutku.here.vo.DataState
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow

interface ChatRepository {

    suspend fun getMessages(): Flow<DataState<List<Message>>>

}
