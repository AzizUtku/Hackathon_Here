package com.azizutku.here.data.repository.chat.datasourceImpl

import com.azizutku.here.data.model.Message
import com.azizutku.here.data.repository.chat.datasource.ChatRemoteDataSource
import kotlinx.coroutines.delay

class ChatRemoteDataSourceImpl : ChatRemoteDataSource {

    override suspend fun getMessages(): List<Message> {
        delay(300)
        return generateMessages()
    }

    private fun generateMessages(): List<Message> {
        return listOf(
            Message(
                id = 0,
                senderName = "Berk",
                message = "Selam",
                isReceived = true,
            ),
            Message(
                id = 1,
                senderName = "Aslı",
                message = "Selam",
                isReceived = true,
            ),
            Message(
                id = 2,
                senderName = "Utku",
                message = "Selam",
                isReceived = false,
            ),
            Message(
                id = 3,
                senderName = "Berk",
                message = "Destek alabilir miyiz?",
                isReceived = true,
            ),
            Message(
                id = 4,
                senderName = "Hazal",
                message = "++",
                isReceived = true,
            ),
            Message(
                id = 5,
                senderName = "Eren",
                message = "Geliyoruz",
                isReceived = true,
            ),
        )
    }

}
