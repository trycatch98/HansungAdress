package trycatch.hs.hansungadress

import android.app.Application
import org.koin.android.ext.android.startKoin
import trycatch.hs.hansungadress.di.appModules

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(this, appModules)
    }
}