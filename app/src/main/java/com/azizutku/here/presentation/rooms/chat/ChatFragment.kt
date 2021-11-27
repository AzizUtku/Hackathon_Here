package com.azizutku.here.presentation.rooms.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.azizutku.here.R
import com.azizutku.here.data.model.Message
import com.azizutku.here.databinding.ChatFragmentBinding
import com.azizutku.here.extensions.hideKeyboard
import com.azizutku.here.presentation.dialogs.AlertDialog
import com.azizutku.here.presentation.dialogs.LoadingDialog
import com.azizutku.here.vo.DataState
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class ChatFragment : Fragment() {

    companion object {
        fun newInstance() = ChatFragment()
    }

    private val viewModel: ChatViewModel by viewModels()
    private lateinit var binding: ChatFragmentBinding

    private val adapter = MessageAdapter()

    @Inject
    lateinit var alertDialog: AlertDialog

    @Inject
    lateinit var loadingDialog: LoadingDialog

    private var startingId = 2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.chat_fragment, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeObservers()
        initUi()
        viewModel.getMessages()
    }

    private fun initUi() {
        binding.toolbar.toolbarTitle.text = getString(R.string.toolbar_title_chat)
        binding.toolbar.toolbarImage.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_left_24)
        binding.recyclerMessages.adapter = adapter
        binding.edtMessage.doOnTextChanged { _, _, _, count ->
            binding.imgSendMessage.setColorFilter(
                ContextCompat.getColor(
                    requireContext(),
                    if (count > 0) {
                        R.color.orange_500
                    } else {
                        R.color.colorGray
                    }
                ),
                android.graphics.PorterDuff.Mode.SRC_IN,
            )
        }
        binding.imgSendMessage.setOnClickListener {
            sendMessage()
        }
    }

    private fun subscribeObservers() {
        viewModel.dataStateMessages.observe(viewLifecycleOwner, { dataState ->
            when (dataState) {
                is DataState.Success<List<Message>> -> {
                    loadingDialog.dismiss()
                    adapter.submitList(dataState.data)
                }
                is DataState.Error -> {
                    loadingDialog.dismiss()
                    alertDialog.setTitle(getString(R.string.title_sorry))
                    alertDialog.setMessage(dataState.throwable.message)
                    alertDialog.show()
                }
                is DataState.Loading -> {
                    loadingDialog.show()
                }
                else -> {
                    // no op
                }
            }
        })
    }

    private fun sendMessage() {
        hideKeyboard()
        val calendar = Calendar.getInstance()
        val currentTime =
            Calendar.getInstance().get(Calendar.HOUR_OF_DAY).toString() + ":" + calendar.get(Calendar.MINUTE)
        val list = adapter.currentList.toMutableList().apply {
            add(
                Message(
                    id = startingId++,
                    message = binding.edtMessage.text.toString(),
                    isReceived = false,
                    senderName = "",
                    time = currentTime,
                )
            )
        }
        adapter.submitList(list)
        binding.edtMessage.setText("")
        binding.recyclerMessages.smoothScrollToPosition(list.size - 1)
    }


}