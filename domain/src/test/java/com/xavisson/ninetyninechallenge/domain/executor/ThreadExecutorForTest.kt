package io.valeet.driver.domain.executor

import com.xavisson.ninetyninechallenge.domain.executor.ThreadExecutor

class ThreadExecutorForTest : ThreadExecutor {
    override fun execute(command: Runnable?) {
        command?.run()
    }
}