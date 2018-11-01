package com.xavisson.ninetyninechallenge.presentation.companylist

import com.xavisson.ninetyninechallenge.base.BasePresenter
import com.xavisson.ninetyninechallenge.domain.company.SubscribeToCompanyListUpdatesUseCase
import com.xavisson.ninetyninechallenge.presentation.navigator.ActivityNavigator
import io.reactivex.rxkotlin.subscribeBy

class CompanyListPresenter(
        private val subscribeToCompanyListUpdatesUseCase: SubscribeToCompanyListUpdatesUseCase,
        private val activityNavigator: ActivityNavigator
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

    fun onCompanyPressed(company: CompanyUI) {
        activityNavigator.goToCompanyDetail(company.id)
    }
}