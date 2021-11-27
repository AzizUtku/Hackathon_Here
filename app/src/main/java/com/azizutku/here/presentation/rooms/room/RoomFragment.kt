package com.azizutku.here.presentation.rooms.room

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.azizutku.here.R
import com.azizutku.here.data.model.Room
import com.azizutku.here.databinding.RoomFragmentBinding
import com.azizutku.here.extensions.setVisible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RoomFragment : Fragment() {

    companion object {
        fun newInstance() = RoomFragment()
    }

    private lateinit var binding: RoomFragmentBinding

    private val args: RoomFragmentArgs by navArgs()
    private val room by lazy { args.room }

    private val adapter = NeedsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.room_fragment, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
    }

    private fun initUi() {
        binding.toolbar.toolbarTitle.text = room.title
        binding.toolbar.toolbarImage.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_left_24)
        binding.recyclerNeeds.adapter = adapter.also {
            it.submitList(room.needList)
        }
        binding.txtTopic.text = room.topic
        binding.txtTitle.text = room.title
        binding.txtDesc.text = room.description
        binding.txtAim.text = "50"
        binding.txtParticipants.text = room.participants.toString()
        binding.txtLocation.text = room.location ?: "Not specified"
        binding.imgPrivate.setVisible(room.isPrivate)
        binding.btnGoChat.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_roomFragment_to_chatFragment)
        }
    }

}
