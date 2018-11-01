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

    private val items = (1..10).map { Company(
            id = it,
            name = "company name $it",
            ric = "ric $it/",
            sharePrice = it.toDouble()) }

    init {
        generate()
    }

    fun generate(): List<Company> {
        val rand = Random(System.currentTimeMillis())
        return items.filter { rand.nextBoolean() }
    }

    fun searchCompanies(): Observable<List<Company>> {
        return companyApi.searchCompanies()
    }
}