package com.azizutku.here.di.rooms

import com.azizutku.here.data.repository.rooms.RoomsRepositoryImpl
import com.azizutku.here.data.repository.rooms.datasource.RoomsRemoteDataSource
import com.azizutku.here.data.repository.rooms.datasourceImpl.RoomsRemoteDataSourceImpl
import com.azizutku.here.domain.repository.rooms.RoomsRepository
import com.azizutku.here.domain.usecase.rooms.GetRoomsOnOrganizationUseCase
import com.azizutku.here.domain.usecase.rooms.GetRoomsOnTurkeyUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RoomModule {

    @Singleton
    @Provides
    fun provideRepository(
        remoteDataSource: RoomsRemoteDataSource,
    ): RoomsRepository {
        return RoomsRepositoryImpl(remoteDataSource)
    }

    @Singleton
    @Provides
    fun provideRemoteDataSource(): RoomsRemoteDataSource {
        return RoomsRemoteDataSourceImpl()
    }

    @Singleton
    @Provides
    fun provideLoginUseCase(repository: RoomsRepository): GetRoomsOnOrganizationUseCase {
        return GetRoomsOnOrganizationUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideGetUserUseCase(repository: RoomsRepository): GetRoomsOnTurkeyUseCase {
        return GetRoomsOnTurkeyUseCase(repository)
    }

}
