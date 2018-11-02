package com.xavisson.ninetyninechallenge.domain.company

import com.xavisson.ninetyninechallenge.domain.executor.ThreadScheduler
import com.xavisson.ninetyninechallenge.domain.executor.applyScheduler
import io.reactivex.Observable


interface SubscribeToCompanyListUpdatesUseCase {
    fun execute(): Observable<List<Company>>
}

class SubscribeToCompanyListUpdates(
        private val companyResource: CompanyResource,
        private val threadScheduler: ThreadScheduler
) : SubscribeToCompanyListUpdatesUseCase {
    override fun execute(): Observable<List<Company>> {
        companyResource.subscribe()
        return companyResource.stream.getObservable()
                .applyScheduler(threadScheduler)
    }
}