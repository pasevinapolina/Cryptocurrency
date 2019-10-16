package by.minsk.polina_pasevina.cryptocurrency.os

import android.app.Application
import by.minsk.polina_pasevina.cryptocurrency.injection.DaggerAppComponent
import by.minsk.polina_pasevina.cryptocurrency.injection.Injector
import by.minsk.polina_pasevina.cryptocurrency.injection.common.ContextModule
import by.minsk.polina_pasevina.cryptocurrency.injection.common.NetworkModule

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initDagger()
    }

    private fun initDagger() {
        val contextModule = ContextModule(applicationContext)
        val appComponent = DaggerAppComponent.builder()
            .contextModule(contextModule)
            .networkModule(NetworkModule())
            .build()
        Injector.init(appComponent)
        Injector.appComponent.inject(this)
    }
}