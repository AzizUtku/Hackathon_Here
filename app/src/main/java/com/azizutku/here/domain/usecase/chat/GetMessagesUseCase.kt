package com.azizutku.here.domain.usecase.chat

import com.azizutku.here.data.model.Message
import com.azizutku.here.domain.repository.chat.ChatRepository
import com.azizutku.here.vo.DataState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMessagesUseCase @Inject constructor(private val repository: ChatRepository) {

    suspend fun execute(): Flow<DataState<List<Message>>> = repository.getMessages()

}
