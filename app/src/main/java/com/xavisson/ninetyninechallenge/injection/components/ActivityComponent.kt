package com.xavisson.ninetyninechallenge.injection.components

import com.xavisson.ninetyninechallenge.injection.PerActivity
import com.xavisson.ninetyninechallenge.injection.modules.ApplicationModule
import dagger.Component

@PerActivity
@Component(
		dependencies = arrayOf(ApplicationComponent::class),
		modules = arrayOf(
				ApplicationModule::class
		))
interface ActivityComponent {
}
