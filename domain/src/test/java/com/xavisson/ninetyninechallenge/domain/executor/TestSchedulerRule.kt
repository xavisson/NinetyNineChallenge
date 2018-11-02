package io.valeet.driver.domain.executor

import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.TestScheduler
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class TestSchedulerRule : TestRule {

    private val testScheduler = TestScheduler()

    fun getTestScheduler(): TestScheduler {
        return testScheduler
    }

    override fun apply(base: Statement, d: Description): Statement {
        return object : Statement() {
            @Throws(Throwable::class)
            override fun evaluate() {
                RxJavaPlugins.setIoSchedulerHandler(
                        { _ -> testScheduler })
                RxJavaPlugins.setComputationSchedulerHandler(
                        { _ -> testScheduler })
                RxJavaPlugins.setNewThreadSchedulerHandler(
                        { _ -> testScheduler })

                try {
                    base.evaluate()
                } finally {
                    RxJavaPlugins.reset()
                }
            }
        }
    }
}