package com.azizutku.here.di.chat

import com.azizutku.here.data.repository.chat.ChatRepositoryImpl
import com.azizutku.here.data.repository.chat.datasource.ChatRemoteDataSource
import com.azizutku.here.data.repository.chat.datasourceImpl.ChatRemoteDataSourceImpl
import com.azizutku.here.domain.repository.chat.ChatRepository
import com.azizutku.here.domain.usecase.chat.GetMessagesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ChatModule {

    @Singleton
    @Provides
    fun provideRepository(
        remoteDataSource: ChatRemoteDataSource,
    ): ChatRepository {
        return ChatRepositoryImpl(remoteDataSource)
    }

    @Singleton
    @Provides
    fun provideRemoteDataSource(): ChatRemoteDataSource {
        return ChatRemoteDataSourceImpl()
    }

    @Singleton
    @Provides
    fun provideLoginUseCase(repository: ChatRepository): GetMessagesUseCase {
        return GetMessagesUseCase(repository)
    }

}
