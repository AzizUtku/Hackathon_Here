package com.azizutku.here.data.repository.rooms.datasource

import com.azizutku.here.data.model.Room

interface RoomsRemoteDataSource {

    suspend fun getRoomsOnOrganization(): List<Room>

    suspend fun getRoomsOnTurkey(): List<Room>

}
