package com.azizutku.here.data.repository.rooms.datasourceImpl

import com.azizutku.here.R
import com.azizutku.here.data.model.Room
import com.azizutku.here.data.repository.rooms.datasource.RoomsRemoteDataSource
import kotlinx.coroutines.delay
import kotlin.random.Random

class RoomsRemoteDataSourceImpl: RoomsRemoteDataSource {

    override suspend fun getRoomsOnOrganization(): List<Room> {
        delay(1200)
        return generateRooms(true)
    }

    override suspend fun getRoomsOnTurkey(): List<Room> {
        delay(1200)
        return generateRooms(false)
    }

    private fun generateRooms(isOnOrganization: Boolean): List<Room> {
        val iconId = if (isOnOrganization) {
            R.drawable.logo_akut
        } else {
            R.drawable.app_logo
        }
        return listOf(
            Room(
                id = 1,
                topic = "Genel",
                title = "Kordinasyon",
                description = "Kordinasyon odası",
                iconId = iconId,
                participants = Random.nextInt(3, 56),
                needs = Random.nextInt(43, 565),
                isPrivate = isOnOrganization,
                location = "Beşiktaş / Istanbul",
            ),
            Room(
                id = 2,
                topic = "Acil",
                title = "8. Bölge",
                description = "8. Bölge'nin ihtiyaçlarının giderilmesi",
                iconId = iconId,
                participants = Random.nextInt(3, 56),
                needs = Random.nextInt(43, 565),
                isPrivate = isOnOrganization && Random.nextBoolean(),
            ),
            Room(
                id = 3,
                topic = "Acil",
                title = "4. Bölge",
                description = "4. Bölge'nin ihtiyaçlarının giderilmesi",
                iconId = iconId,
                participants = Random.nextInt(3, 56),
                needs = Random.nextInt(43, 565),
                isPrivate = isOnOrganization && Random.nextBoolean(),
            ),
            Room(
                id = 4,
                topic = "İhtiyaç",
                title = "Kıyafet",
                description = "İstanbul / Kadıköy bölgesi kıyafet yardımı",
                iconId = iconId,
                participants = Random.nextInt(3, 56),
                needs = Random.nextInt(43, 565),
                isPrivate = isOnOrganization && Random.nextBoolean(),
                location = "Kadıköy / Istanbul",
            ),
            Room(
                id = 5,
                topic = "İdari",
                title = "Yönetim",
                description = "Yönetim odası",
                iconId = iconId,
                participants = Random.nextInt(3, 16),
                needs = Random.nextInt(3, 13),
                isPrivate = isOnOrganization && Random.nextBoolean(),
            ),
        )
    }

}
