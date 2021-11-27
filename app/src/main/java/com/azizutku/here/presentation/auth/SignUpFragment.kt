package com.azizutku.here.presentation.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.azizutku.here.R
import com.azizutku.here.databinding.SignUpFragmentBinding
import com.azizutku.here.extensions.setVisible
import com.azizutku.here.presentation.dialogs.list.SimpleListDialog
import com.azizutku.here.presentation.dialogs.list.SimpleWideAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SignUpFragment : Fragment() {

    companion object {
        fun newInstance() = SignUpFragment()
    }

    private lateinit var viewModel: SignUpViewModel
    private lateinit var binding: SignUpFragmentBinding

    @Inject
    lateinit var simpleListDialog: SimpleListDialog<Int, String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.sign_up_fragment, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
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