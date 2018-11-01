package com.xavisson.ninetyninechallenge.data.adapter

import com.xavisson.ninetyninechallenge.data.exception.NinetyNineServerExceptionFactory
import io.reactivex.Observable
import io.reactivex.functions.Function
import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

class RxObservableErrorCallAdapter constructor(private val wrapped: CallAdapter<Any, Observable<*>>) : CallAdapter<Any, Observable<*>> {
    override fun responseType(): Type {
        return wrapped.responseType()
    }

    @SuppressWarnings("unchecked")
    override fun adapt(call: Call<Any>): Observable<*> {
        return wrapped.adapt(call)
                .onErrorResumeNext(Function { throwable ->
                    Observable.error(NinetyNineServerExceptionFactory.create(throwable))
                })
    }
}