package com.azizutku.here.data.repository.rooms

import com.azizutku.here.data.model.Room
import com.azizutku.here.data.model.User
import com.azizutku.here.data.repository.auth.datasource.AuthCacheDataSource
import com.azizutku.here.data.repository.auth.datasource.AuthRemoteDataSource
import com.azizutku.here.data.repository.rooms.datasource.RoomsRemoteDataSource
import com.azizutku.here.domain.repository.auth.AuthRepository
import com.azizutku.here.domain.repository.rooms.RoomsRepository
import com.azizutku.here.vo.DataState
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject

class RoomsRepositoryImpl
@Inject constructor(
    private val remoteDataSource: RoomsRemoteDataSource,
) : RoomsRepository {

    override suspend fun getRoomsOnOrganization(): Flow<DataState<List<Room>>> = flow {
        emit(DataState.Loading)
        runCatching {
            val rooms = remoteDataSource.getRoomsOnOrganization()
            emit(DataState.Success(rooms))
        }.onFailure {
            Timber.i(it)
            emit(DataState.Error(it))
        }
    }

    override suspend fun getRoomsOnTurkey(): Flow<DataState<List<Room>>> = flow {
        emit(DataState.Loading)
        runCatching {
            val rooms = remoteDataSource.getRoomsOnTurkey()
            emit(DataState.Success(rooms))
        }.onFailure {
            Timber.i(it)
            emit(DataState.Error(it))
        }
    }

}
