package com.xavisson.ninetyninechallenge

import android.app.Application
import com.xavisson.ninetyninechallenge.domain.logger.Kog
import com.xavisson.ninetyninechallenge.domain.logger.Logger
import com.xavisson.ninetyninechallenge.logger.AndroidLogger

class NinetyNineChallengeApplication : Application() {

	override fun onCreate() {
		super.onCreate()
		initLogger()
	}

	private fun initLogger() {
		Kog.plant(AndroidLogger())
		Logger.i({ "Logger planted" })
	}
}
