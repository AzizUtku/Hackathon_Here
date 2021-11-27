package com.azizutku.here.data.repository.chat

import com.azizutku.here.data.model.Message
import com.azizutku.here.data.model.Room
import com.azizutku.here.data.model.User
import com.azizutku.here.data.repository.auth.datasource.AuthCacheDataSource
import com.azizutku.here.data.repository.auth.datasource.AuthRemoteDataSource
import com.azizutku.here.data.repository.chat.datasource.ChatRemoteDataSource
import com.azizutku.here.data.repository.rooms.datasource.RoomsRemoteDataSource
import com.azizutku.here.domain.repository.auth.AuthRepository
import com.azizutku.here.domain.repository.chat.ChatRepository
import com.azizutku.here.domain.repository.rooms.RoomsRepository
import com.azizutku.here.vo.DataState
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject

class ChatRepositoryImpl
@Inject constructor(
    private val remoteDataSource: ChatRemoteDataSource,
) : ChatRepository {

    override suspend fun getMessages(): Flow<DataState<List<Message>>> = flow {
        emit(DataState.Loading)
        runCatching {
            val messages = remoteDataSource.getMessages()
            emit(DataState.Success(messages))
        }.onFailure {
            Timber.i(it)
            emit(DataState.Error(it))
        }
    }

}
