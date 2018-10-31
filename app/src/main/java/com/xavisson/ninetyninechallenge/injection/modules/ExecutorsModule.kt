package com.xavisson.ninetyninechallenge.injection.modules


import com.xavisson.ninetyninechallenge.domain.executor.PostExecutionThread
import com.xavisson.ninetyninechallenge.domain.executor.ThreadExecutor
import com.xavisson.ninetyninechallenge.domain.executor.ThreadScheduler
import com.xavisson.ninetyninechallenge.executor.DefaultThreadScheduler
import com.xavisson.ninetyninechallenge.executor.JobExecutor
import com.xavisson.ninetyninechallenge.executor.UIPostExecutionThread
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
open class ExecutorsModule {

    @Provides
    @Singleton
    open fun providesThreadExecutor(): ThreadExecutor = JobExecutor()

    @Provides
    @Singleton
    open fun providesPostExecutionThread(): PostExecutionThread = UIPostExecutionThread()

    @Provides
    @Singleton
    open fun providesThreadScheduler(
            threadExecutor: ThreadExecutor,
            postExecutionThread: PostExecutionThread
    ): ThreadScheduler {
        return DefaultThreadScheduler(
                threadExecutor = threadExecutor,
                postExecutionThread = postExecutionThread
        )
    }
}