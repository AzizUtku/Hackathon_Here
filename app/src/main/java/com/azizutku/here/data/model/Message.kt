package com.azizutku.here.data.model

data class Message(
    val id: Int,
    val senderName: String,
    val message: String,
    val isReceived: Boolean = true,
    val time: String = "18:49"
)
