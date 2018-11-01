package com.xavisson.ninetyninechallenge.data.adapter

import com.xavisson.ninetyninechallenge.data.exception.NinetyNineServerExceptionFactory
import io.reactivex.Completable
import io.reactivex.functions.Function
import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

class RxCompletableErrorCallAdapter constructor(private val wrapped: CallAdapter<Any, Completable>) : CallAdapter<Any, Completable> {
    override fun responseType(): Type {
        return wrapped.responseType()
    }

    @SuppressWarnings("unchecked")
    override fun adapt(call: Call<Any>): Completable {
        return wrapped.adapt(call)
                .onErrorResumeNext(Function { throwable ->
                    Completable.error(NinetyNineServerExceptionFactory.create(throwable))
                })
    }
}