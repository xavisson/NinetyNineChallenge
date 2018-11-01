package com.xavisson.ninetyninechallenge.domain.company

import com.xavisson.ninetyninechallenge.domain.reactive.Stream
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import java.util.*


class CompanyListStream : Stream<List<Company>>(BehaviorSubject.create())

class CompanyResource(
        private val modelStream: CompanyListStream,
        private val companyApi: CompanyApi
) {
    val stream get() = modelStream

    var currentCompanyList: List<Company> = emptyList()

    fun searchCompanies(): Observable<List<Company>> {
        return companyApi.searchCompanies()
                .doOnNext { currentCompanyList = it }
    }

    fun searchCompanyWithId(companyId: Int): Observable<Company> {
        return Observable.just(currentCompanyList.find { it.id == companyId })
    }
}