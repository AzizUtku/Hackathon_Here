package com.azizutku.here.presentation.rooms

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.azizutku.here.data.model.Room
import com.azizutku.here.data.model.User
import com.azizutku.here.domain.usecase.auth.GetUserUseCase
import com.azizutku.here.domain.usecase.auth.LoginUseCase
import com.azizutku.here.domain.usecase.auth.SignupUseCase
import com.azizutku.here.domain.usecase.rooms.GetRoomsOnOrganizationUseCase
import com.azizutku.here.domain.usecase.rooms.GetRoomsOnTurkeyUseCase
import com.azizutku.here.vo.DataState
import com.azizutku.here.vo.SingleLiveEvent
import com.google.firebase.auth.AuthResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoomsViewModel @Inject constructor(
    private val getRoomsOnTurkeyUseCase: GetRoomsOnTurkeyUseCase,
    private val getRoomsOnOrganizationUseCase: GetRoomsOnOrganizationUseCase,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _dataStateRoomOrganization: SingleLiveEvent<DataState<List<Room>>> = SingleLiveEvent()
    val dataStateRoomOrganization: LiveData<DataState<List<Room>>>
        get() = _dataStateRoomOrganization

    private val _dataStateRoomTurkey: SingleLiveEvent<DataState<List<Room>>> = SingleLiveEvent()
    val dataStateRoomTurkey: LiveData<DataState<List<Room>>>
        get() = _dataStateRoomTurkey

    fun getRoomsOnOrganization() {
        viewModelScope.launch {
            getRoomsOnOrganizationUseCase.execute().onEach {
                _dataStateRoomOrganization.value = it
            }.launchIn(viewModelScope)
        }
    }

    fun getRoomsOnTurkey() {
        viewModelScope.launch {
            getRoomsOnTurkeyUseCase.execute().onEach {
                _dataStateRoomTurkey.value = it
            }.launchIn(viewModelScope)
        }
    }

}
