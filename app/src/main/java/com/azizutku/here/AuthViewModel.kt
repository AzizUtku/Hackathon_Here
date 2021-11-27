package com.azizutku.here

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.azizutku.here.domain.usecase.auth.GetUserUseCase
import com.azizutku.here.domain.usecase.auth.LoginUseCase
import com.azizutku.here.vo.DataState
import com.azizutku.here.vo.SingleLiveEvent
import com.google.firebase.auth.AuthResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _dataStateUser: SingleLiveEvent<DataState<AuthResult>> = SingleLiveEvent()
    val dataStateUser: LiveData<DataState<AuthResult>>
        get() = _dataStateUser

    fun login(email: String, password: String) {
        viewModelScope.launch {
            loginUseCase.execute(email, password).onEach {
                _dataStateUser.value = it
            }.launchIn(viewModelScope)
        }
    }

    fun getUserFromCache() {
        viewModelScope.launch {
            getUserUseCase.execute().onEach {
                _dataStateUser.value = it
            }.launchIn(viewModelScope)
        }
    }

}
