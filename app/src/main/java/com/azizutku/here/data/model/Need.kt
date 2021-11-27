package com.azizutku.here.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Need(
    val id: Int,
    val title: String,
    val description: String,
    val filled: Int,
    val needed: Int,
): Parcelable {

    companion object {
        fun generateNeeds(): List<Need> {
            return listOf(
                Need(
                    id = 1,
                    title = "Çadır",
                    description = "Çift kişilik",
                    filled = 4,
                    needed = 5,
                ),
                Need(
                    id = 1,
                    title = "Kazak",
                    description = "Her türlü beden",
                    filled = 15,
                    needed = 32,
                ),
                Need(
                    id = 1,
                    title = "Ceket",
                    description = "Her türlü beden",
                    filled = 4,
                    needed = 16,
                ),
                Need(
                    id = 1,
                    title = "Pantolon",
                    description = "Her türlü beden",
                    filled = 7,
                    needed = 6,
                ),
            )
        }
    }
}
