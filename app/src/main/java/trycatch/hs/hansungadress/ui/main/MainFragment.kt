package trycatch.hs.hansungadress.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.error
import org.koin.android.viewmodel.ext.android.viewModel
import trycatch.hs.hansungadress.R
import trycatch.hs.hansungadress.databinding.FragmentMainBinding
import trycatch.hs.hansungadress.ui.login.LoginViewModel
import java.util.*

class MainFragment : Fragment(), AnkoLogger {
    private lateinit var binding: FragmentMainBinding
    private val mainViewModel: MainViewModel by viewModel()
    private val loginViewModel: LoginViewModel by viewModel()
    private val adapter by lazy {
        SearchAdapter(layoutInflater, Vector())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.vm = mainViewModel

        if (!mainViewModel.isLoginStatus())
            findNavController().navigate(R.id.action_mainFragment_to_loginFragment)
        else{
            error { "relogin" }
        loginViewModel.reLogin()}

        binding.searchResultLayout.searchResultList.adapter = adapter

        mainViewModel.searchResult.observe(this, Observer {
            adapter.setItems(it)
            binding.searchResultLayout.numberOfResult.text = it.size.toString()
            binding.searchResultLayout.root.visibility = View.VISIBLE
            binding.favoriteLayout.root.visibility = View.GONE
            binding.etSearch.clearFocus()
            binding.searchCancelBtn.visibility = View.VISIBLE
        })

        binding.searchCancelBtn.setOnClickListener {
            binding.favoriteLayout.root.visibility = View.VISIBLE
            binding.searchResultLayout.root.visibility = View.GONE
            binding.searchCancelBtn.visibility = View.GONE
            mainViewModel.query.value = ""
        }

        return binding.root
    }
}