package com.azizutku.here.data.model

data class Room(
    val id: Int,
    val topic: String,
    val title: String,
    val description: String,
    val iconId: Int,
    val participants: Int,
    val needs: Int,
    val location: String? = null,
    val isPrivate: Boolean = false,
)
