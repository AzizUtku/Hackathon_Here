package com.azizutku.here.domain.repository.rooms

import com.azizutku.here.data.model.Room
import com.azizutku.here.data.model.User
import com.azizutku.here.vo.DataState
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow

interface RoomsRepository {

    suspend fun getRoomsOnOrganization(): Flow<DataState<List<Room>>>
    suspend fun getRoomsOnTurkey(): Flow<DataState<List<Room>>>

}
