package com.azizutku.here.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
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
    var needList: List<Need> = Need.generateNeeds()
) : Parcelable
