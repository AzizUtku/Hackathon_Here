package com.azizutku.here.domain.usecase.rooms

import com.azizutku.here.data.model.Room
import com.azizutku.here.domain.repository.rooms.RoomsRepository
import com.azizutku.here.vo.DataState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRoomsOnTurkeyUseCase @Inject constructor(private val repository: RoomsRepository) {

    suspend fun execute(): Flow<DataState<List<Room>>> = repository.getRoomsOnTurkey()

}
