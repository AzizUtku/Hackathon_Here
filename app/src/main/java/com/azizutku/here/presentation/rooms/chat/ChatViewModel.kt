package com.azizutku.here.presentation.rooms.chat

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.azizutku.here.data.model.Message
import com.azizutku.here.domain.usecase.chat.GetMessagesUseCase
import com.azizutku.here.vo.DataState
import com.azizutku.here.vo.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val getMessagesUseCase: GetMessagesUseCase,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _dataStateMessages: SingleLiveEvent<DataState<List<Message>>> = SingleLiveEvent()
    val dataStateMessages: LiveData<DataState<List<Message>>>
        get() = _dataStateMessages

    fun getMessages() {
        viewModelScope.launch {
            getMessagesUseCase.execute().onEach {
                _dataStateMessages.value = it
            }.launchIn(viewModelScope)
        }
    }

}
