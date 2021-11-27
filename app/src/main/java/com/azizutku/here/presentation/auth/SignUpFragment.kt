package com.azizutku.here.presentation.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.azizutku.here.AuthViewModel
import com.azizutku.here.R
import com.azizutku.here.data.model.User
import com.azizutku.here.databinding.SignUpFragmentBinding
import com.azizutku.here.extensions.setVisible
import com.azizutku.here.presentation.dialogs.AlertDialog
import com.azizutku.here.presentation.dialogs.LoadingDialog
import com.azizutku.here.presentation.dialogs.list.SimpleListDialog
import com.azizutku.here.presentation.dialogs.list.SimpleWideAdapter
import com.azizutku.here.vo.DataState
import com.google.firebase.auth.AuthResult
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SignUpFragment : Fragment() {

    companion object {
        fun newInstance() = SignUpFragment()
    }

    private val authViewModel: AuthViewModel by viewModels()
    private lateinit var binding: SignUpFragmentBinding

    @Inject
    lateinit var simpleListDialog: SimpleListDialog<Int, String>

    @Inject
    lateinit var alertDialog: AlertDialog

    @Inject
    lateinit var loadingDialog: LoadingDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.sign_up_fragment, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = authViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeObservers()
        initUi()
    }

    private fun subscribeObservers() {
        subscribeAuth()
    }

    private fun subscribeAuth() {
        authViewModel.dataStateAuthUser.observe(viewLifecycleOwner, { dataState ->
            when (dataState) {
                is DataState.Success<User> -> {
                    loadingDialog.dismiss()
                    findNavController().navigate(R.id.action_signUpFragment_to_roomsFragment)
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

    private fun initUi() {
        binding.toolbar.toolbarTitle.text = resources.getString(R.string.text_create_an_account)
        binding.txtSignIn.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_signUpFragment_to_signInFragment)
        }
        binding.checkMemberOfCivilSocietyOrganization.setOnCheckedChangeListener { _, isChecked ->
            binding.inputMemberOfCivilSociety.setVisible(isChecked)
            binding.inputEntranceCode.setVisible(isChecked)
        }
        binding.edtMemberOfCivilSociety.setOnClickListener {
            val simpleWideAdapter = SimpleWideAdapter<Int, String>()
            simpleListDialog.initRecyclerView(simpleWideAdapter)
            val list = mutableListOf(
                Pair(0, "AKUT Arama Kurtarma Derneği"),
                Pair(1, "AÇEV Anne Çocuk Eğitim Vakfı"),
                Pair(2, "Darüşşafaka Cemiyeti"),
                Pair(3, "TEMA Vakfı"),
                Pair(4, "Türkiye Down Sendromu Derneği"),
                Pair(5, "WWF-Türkiye"),
                Pair(6, "Türk Kızılayı"),
            )
            simpleListDialog.setItems(list)
            simpleListDialog.setOnSimpleItemClicked { _, value ->
                binding.edtMemberOfCivilSociety.setText(value)
                simpleListDialog.dismiss()
            }
            simpleListDialog.show()
        }
    }

}