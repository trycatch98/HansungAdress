package trycatch.hs.hansungadress.di

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module
import trycatch.hs.hansungadress.data.local.PreferencesManager

val preferencesModules = module {
    single {
        PreferencesManager(androidContext())
    }
}