package com.xavisson.ninetyninechallenge.presentation.companylist

import com.xavisson.ninetyninechallenge.base.BasePresenter
import com.xavisson.ninetyninechallenge.domain.company.SubscribeToCompanyListUpdatesUseCase
import io.reactivex.rxkotlin.subscribeBy

class CompanyListPresenter(
        private val subscribeToCompanyListUpdatesUseCase: SubscribeToCompanyListUpdatesUseCase
) : BasePresenter<CompanyListView>() {

    override fun onCreate() {
        super.onCreate()
        getCompanies()
    }

    fun getCompanies() {
        subscribeToCompanyListUpdatesUseCase.execute()
                .subscribeBy(
                        onNext = { getView()?.showCompanyData(it.toUi()) }
                )
    }
}