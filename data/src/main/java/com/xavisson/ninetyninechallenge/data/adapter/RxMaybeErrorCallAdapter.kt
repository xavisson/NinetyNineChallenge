package com.xavisson.ninetyninechallenge.data.adapter

import com.xavisson.ninetyninechallenge.data.exception.NinetyNineServerExceptionFactory
import io.reactivex.Maybe
import io.reactivex.functions.Function
import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

class RxMaybeErrorCallAdapter constructor(private val wrapped: CallAdapter<Any, Maybe<*>>) : CallAdapter<Any, Maybe<*>> {
    override fun responseType(): Type {
        return wrapped.responseType()
    }

    @SuppressWarnings("unchecked")
    override fun adapt(call: Call<Any>): Maybe<*> {
        return wrapped.adapt(call)
                .onErrorResumeNext(Function { throwable ->
                    Maybe.error(NinetyNineServerExceptionFactory.create(throwable))
                })
    }
}