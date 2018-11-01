package com.xavisson.ninetyninechallenge.data.adapter

import com.xavisson.ninetyninechallenge.data.exception.NinetyNineServerExceptionFactory
import io.reactivex.Flowable
import io.reactivex.functions.Function
import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

class RxFlowableErrorCallAdapter constructor(private val wrapped: CallAdapter<Any, Flowable<*>>) : CallAdapter<Any, Flowable<*>> {
    override fun responseType(): Type {
        return wrapped.responseType()
    }

    @SuppressWarnings("unchecked")
    override fun adapt(call: Call<Any>): Flowable<*> {
        return wrapped.adapt(call)
                .onErrorResumeNext(Function { throwable ->
                    Flowable.error(NinetyNineServerExceptionFactory.create(throwable))
                })
    }
}