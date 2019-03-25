package trycatch.hs.hansungadress.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.support.v4.toast
import org.koin.android.viewmodel.ext.android.viewModel
import trycatch.hs.hansungadress.R
import trycatch.hs.hansungadress.databinding.FragmentLoginBinding

class LoginFragment : Fragment(), AnkoLogger {
    private lateinit var binding: FragmentLoginBinding
    private val loginViewModel by viewModel<LoginViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.vm = loginViewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loginViewModel.success.observe(this, Observer {
            if (it)
                findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
        })

        loginViewModel.error.observe(this, Observer {
            toast("아이디 또는 비밀번호를 확인해주세요.")
        })
    }
}