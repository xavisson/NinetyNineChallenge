package com.xavisson.ninetyninechallenge.data.exception

import io.reactivex.exceptions.CompositeException
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.TimeoutException

class NinetyNineServerExceptionFactory {
    companion object {
        fun create(_throwable: Throwable): NinetyNineServerException {
            var throwable = _throwable
            when (throwable) {
                is CompositeException -> throwable = getFirstExceptionFromComposite(throwable)
                is HttpException -> return NinetyNineServerException.httpError(throwable)
                is IOException, is TimeoutException -> return NinetyNineServerException.networkError(throwable)
            }
            return NinetyNineServerException.undefinedError(throwable)
        }

        private fun getFirstExceptionFromComposite(throwable: Throwable): Throwable {
            return (throwable as CompositeException).exceptions[0]
        }
    }
}