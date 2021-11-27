package com.azizutku.here.presentation.rooms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.azizutku.here.R
import com.azizutku.here.data.model.Room
import com.azizutku.here.databinding.RoomsFragmentBinding
import com.azizutku.here.presentation.dialogs.AlertDialog
import com.azizutku.here.presentation.dialogs.LoadingDialog
import com.azizutku.here.vo.DataState
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import android.opengl.ETC1.getHeight
import android.widget.LinearLayout

import androidx.recyclerview.widget.RecyclerView

import androidx.recyclerview.widget.LinearLayoutManager




@AndroidEntryPoint
class RoomsFragment : Fragment() {

    companion object {
        fun newInstance() = RoomsFragment()
    }

    private val viewModel: RoomsViewModel by viewModels()
    private lateinit var binding: RoomsFragmentBinding

    private val adapterOrganization = RoomsAdapter().apply {
        setWidthPercent(0.80f)
        setOnItemClicked(::onRoomClicked)
    }
    private val adapterTurkey = RoomsAdapter().apply {
        setOnItemClicked(::onRoomClicked)
    }

    @Inject
    lateinit var alertDialog: AlertDialog

    @Inject
    lateinit var loadingDialog: LoadingDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.rooms_fragment, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeObservers()
        initUi()
        viewModel.getRoomsOnOrganization()
        viewModel.getRoomsOnTurkey()
    }

    private fun initUi() {
        binding.toolbar.toolbarTitle.text = getString(R.string.title_toolbar_activities)
        binding.recyclerRoomsOnOrganization.layoutManager = object : LinearLayoutManager(
            requireContext(),
            HORIZONTAL,
            false
        ) {
            override fun checkLayoutParams(lp: RecyclerView.LayoutParams): Boolean {
                lp.width = (width * 0.8).toInt()
                return true
            }
        }
        binding.recyclerRoomsOnOrganization.adapter = adapterOrganization
        binding.recyclerRoomsOnTurkey.adapter = adapterTurkey
    }

    private fun subscribeObservers() {
        viewModel.dataStateRoomOrganization.observe(viewLifecycleOwner, { dataState ->
            when (dataState) {
                is DataState.Success<List<Room>> -> {
                    loadingDialog.dismiss()
                    adapterOrganization.submitList(dataState.data)
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

        viewModel.dataStateRoomTurkey.observe(viewLifecycleOwner, { dataState ->
            when (dataState) {
                is DataState.Success<List<Room>> -> {
                    loadingDialog.dismiss()
                    adapterTurkey.submitList(dataState.data)
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

    private fun onRoomClicked(room: Room) {
        // TODO: Implement method
    }

}
