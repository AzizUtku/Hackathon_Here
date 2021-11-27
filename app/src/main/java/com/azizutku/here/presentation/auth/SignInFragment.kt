package com.azizutku.here.presentation.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.azizutku.here.AuthViewModel
import com.azizutku.here.R
import com.azizutku.here.databinding.SignInFragmentBinding
import com.azizutku.here.presentation.dialogs.AlertDialog
import com.azizutku.here.presentation.dialogs.LoadingDialog
import com.azizutku.here.vo.DataState
import com.google.firebase.auth.AuthResult
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SignInFragment : Fragment() {

    companion object {
        fun newInstance() = SignInFragment()
    }

    private val authViewModel: AuthViewModel by viewModels()
    private lateinit var binding: SignInFragmentBinding

    @Inject
    lateinit var alertDialog: AlertDialog

    @Inject
    lateinit var loadingDialog: LoadingDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.sign_in_fragment, container, false)
        binding.lifecycleOwner = this
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
        authViewModel.dataStateUser.observe(viewLifecycleOwner, { dataState ->
            when (dataState) {
                is DataState.Success<AuthResult> -> {
                    loadingDialog.dismiss()
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
        binding.authBtnLogin.setOnClickListener {
            authViewModel.login(binding.signinEdtEmail.text.toString(), binding.signinEdtPassword.text.toString())
        }
        binding.signinTxtSignUp.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_signInFragment_to_signUpFragment)
        }
    }


}