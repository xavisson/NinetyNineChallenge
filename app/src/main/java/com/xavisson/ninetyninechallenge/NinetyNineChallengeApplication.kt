package com.xavisson.ninetyninechallenge

import android.app.Application
import com.xavisson.ninetyninechallenge.domain.logger.Kog
import com.xavisson.ninetyninechallenge.domain.logger.Logger
import com.xavisson.ninetyninechallenge.injection.components.ApplicationComponent
import com.xavisson.ninetyninechallenge.injection.components.DaggerApplicationComponent
import com.xavisson.ninetyninechallenge.injection.modules.ApplicationModule
import com.xavisson.ninetyninechallenge.logger.AndroidLogger

class NinetyNineChallengeApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        NinetyNineChallengeApplication.application = this
        initLogger()
    }

    private fun initLogger() {
        Kog.plant(AndroidLogger())
        Logger.i({ "Logger planted" })
    }

    companion object {
        private lateinit var application: NinetyNineChallengeApplication

        val applicationComponent: ApplicationComponent by lazy {
            val appComponent = DaggerApplicationComponent.builder()
                    .applicationModule(ApplicationModule(application))
                    .build()
            appComponent.inject(application)
            appComponent
        }
    }
}
