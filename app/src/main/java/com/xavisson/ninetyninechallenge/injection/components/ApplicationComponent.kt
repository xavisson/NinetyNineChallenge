package com.xavisson.ninetyninechallenge.injection.components

import com.xavisson.ninetyninechallenge.NinetyNineChallengeApplication
import com.xavisson.ninetyninechallenge.injection.modules.ApplicationModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
		ApplicationModule::class))
interface ApplicationComponent {
	fun inject(application: NinetyNineChallengeApplication)
}
