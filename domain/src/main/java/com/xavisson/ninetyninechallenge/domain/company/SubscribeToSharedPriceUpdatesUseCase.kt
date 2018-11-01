package com.xavisson.ninetyninechallenge.domain.company

import com.xavisson.ninetyninechallenge.domain.executor.ThreadScheduler
import com.xavisson.ninetyninechallenge.domain.executor.applyScheduler
import io.reactivex.Observable


interface SubscribeToSharedPriceUpdatesUseCase {
    fun execute(companyId: Int): Observable<Double>
}

class SubscribeToSharedPriceUpdates(
        private val companyResource: CompanyResource,
        private val threadScheduler: ThreadScheduler
) : SubscribeToSharedPriceUpdatesUseCase {
    override fun execute(companyId: Int): Observable<Double> {
        return companyResource.stream.getObservable()
                .applyScheduler(threadScheduler)
                .flatMapIterable { company -> company }
                .filter { it.id == companyId }
                .map { it.sharePrice }
    }
}