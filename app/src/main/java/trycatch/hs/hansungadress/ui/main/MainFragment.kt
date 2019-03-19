package trycatch.hs.hansungadress.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.koin.android.ext.android.inject
import trycatch.hs.hansungadress.R
import trycatch.hs.hansungadress.data.local.PreferencesManager
import trycatch.hs.hansungadress.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private val prefs: PreferencesManager by inject()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        if (!prefs.getLoginStatus())
            findNavController().navigate(R.id.action_mainFragment_to_loginFragment)
        return binding.root
    }
}