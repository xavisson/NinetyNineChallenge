package com.xavisson.ninetyninechallenge.domain.company

import com.xavisson.ninetyninechallenge.domain.executor.ThreadScheduler
import com.xavisson.ninetyninechallenge.domain.executor.applyScheduler
import io.reactivex.Observable

interface GetCompanyDetailsUseCase {
    fun execute(companyId: Int) : Observable<Company>
}

class GetCompanyDetails(
        private val companyResource: CompanyResource,
        private val threadScheduler: ThreadScheduler
) : GetCompanyDetailsUseCase {
    override fun execute(companyId: Int): Observable<Company> {
        return companyResource.searchCompanyWithId(companyId)
                .applyScheduler(threadScheduler)
    }
}