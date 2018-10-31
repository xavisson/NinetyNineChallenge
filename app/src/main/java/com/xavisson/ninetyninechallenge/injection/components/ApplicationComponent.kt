package com.xavisson.ninetyninechallenge.injection.components

import com.xavisson.ninetyninechallenge.NinetyNineChallengeApplication
import com.xavisson.ninetyninechallenge.domain.executor.PostExecutionThread
import com.xavisson.ninetyninechallenge.domain.executor.ThreadExecutor
import com.xavisson.ninetyninechallenge.domain.executor.ThreadScheduler
import com.xavisson.ninetyninechallenge.injection.modules.ApplicationModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        ApplicationModule::class))
interface ApplicationComponent {

    fun inject(application: NinetyNineChallengeApplication)

    fun threadScheduler(): ThreadScheduler

    fun threadExecutor(): ThreadExecutor

    fun postExecutionThread(): PostExecutionThread
}
