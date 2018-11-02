package com.xavisson.ninetyninechallenge.domain.company

import com.xavisson.ninetyninechallenge.domain.executor.ThreadExecutor
import com.xavisson.ninetyninechallenge.domain.reactive.DisposeBag
import com.xavisson.ninetyninechallenge.domain.reactive.Stream
import com.xavisson.ninetyninechallenge.domain.reactive.addDisposableTo
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import java.util.concurrent.TimeUnit


class CompanyListStream : Stream<List<Company>>(BehaviorSubject.create())

class CompanyResource(
        private val modelStream: CompanyListStream,
        private val companyApi: CompanyApi,
        private val threadExecutor: ThreadExecutor
) {

    companion object {
        const val POLLING_INTERVAL_IN_SECONDS = 10L
    }

    val stream get() = modelStream

    var currentCompanyList: List<Company> = emptyList()

    private var companiesSubscriptionDisposeBag = DisposeBag()

    init {
        subscribe()
    }

    fun subscribe() {
        companiesSubscriptionDisposeBag.dispose()
        val companyUpdatesFromPolling = pollingCompanies()
        companyUpdatesFromPolling
                .subscribeOn(Schedulers.from(threadExecutor))
                .onErrorResumeNext { throwable: Throwable ->
                    currentCompanyList = emptyList()
                    Observable.just(currentCompanyList)
                }
                .subscribeToPolling()
                .addDisposableTo(companiesSubscriptionDisposeBag)
    }

    private fun pollingCompanies(): Observable<List<Company>> {
        return Observable.interval(0, POLLING_INTERVAL_IN_SECONDS, TimeUnit.SECONDS)
                .switchMap<List<Company>> {
                    fetchCompanies()
                }
    }

    private fun fetchCompanies(): Observable<List<Company>> {
        return companyApi.searchCompanies()
                .map { it.sortedBy { it.sharePrice } }
                .doOnNext { currentCompanyList = it }
    }

    fun processCompaniesUpdate(companies: List<Company>) {
        currentCompanyList = companies
        stream.publish(companies)
    }

    fun searchCompanyWithId(companyId: Int): Observable<Company> {
        return companyApi.searchCompanyById(companyId)
    }

    private fun Observable<List<Company>>.subscribeToPolling(): Disposable {
        return this.subscribeBy(
                onNext = {
                    processCompaniesUpdate(it)
                }
        )
    }
}