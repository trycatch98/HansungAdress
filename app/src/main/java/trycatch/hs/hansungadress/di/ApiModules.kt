package trycatch.hs.hansungadress.di

import org.koin.dsl.module.module
import trycatch.hs.hansungadress.data.remote.InfoApiService

val apiModules = module {
    single {
        InfoApiService()
    }
}