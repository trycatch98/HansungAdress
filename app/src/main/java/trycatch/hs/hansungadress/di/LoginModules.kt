package trycatch.hs.hansungadress.di

import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import trycatch.hs.hansungadress.ui.login.LoginViewModel

val loginModules = module {
    viewModel {
        LoginViewModel(get(), get())
    }
}