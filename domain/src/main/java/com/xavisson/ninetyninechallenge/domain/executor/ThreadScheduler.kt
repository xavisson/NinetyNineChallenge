package com.xavisson.ninetyninechallenge.domain.executor

import io.reactivex.CompletableTransformer
import io.reactivex.ObservableTransformer

interface ThreadScheduler {
    fun <T> apply(): ObservableTransformer<T, T>
    fun applyCompletable(): CompletableTransformer
}