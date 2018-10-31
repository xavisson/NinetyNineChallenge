package com.xavisson.ninetyninechallenge.domain.executor

import io.reactivex.Scheduler

interface PostExecutionThread {
    fun getScheduler(): Scheduler
}