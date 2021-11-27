package com.azizutku.here.data.repository.chat.datasource

import com.azizutku.here.data.model.Message

interface ChatRemoteDataSource {

    suspend fun getMessages(): List<Message>

}
