package co.com.monkeymobile.letscook

import android.app.Application
import co.com.monkeymobile.letscook.core.di.ApplicationComponent
import co.com.monkeymobile.letscook.core.di.ApplicationModule
import co.com.monkeymobile.letscook.core.di.DaggerApplicationComponent

class AndroidApplication : Application() {

    val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)
    }
}