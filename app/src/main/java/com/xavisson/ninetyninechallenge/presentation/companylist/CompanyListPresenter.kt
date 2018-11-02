package com.xavisson.ninetyninechallenge.presentation.companylist

import com.xavisson.ninetyninechallenge.base.BasePresenter
import com.xavisson.ninetyninechallenge.domain.company.SubscribeToCompanyListUpdatesUseCase
import com.xavisson.ninetyninechallenge.domain.logger.Logger
import com.xavisson.ninetyninechallenge.presentation.navigator.ActivityNavigator
import io.reactivex.rxkotlin.subscribeBy

class CompanyListPresenter(
        private val subscribeToCompanyListUpdatesUseCase: SubscribeToCompanyListUpdatesUseCase,
        private val activityNavigator: ActivityNavigator
) : BasePresenter<CompanyListView>() {

    fun getCompanies() {
        subscribeToCompanyListUpdatesUseCase.execute()
                .subscribeBy(
                        onNext = {
                            if (it.isEmpty()) {
                                getView()?.showErrorMessage()
                            } else {
                                getView()?.showCompanyData(it.toUi())
                            }
                        }
                )
    }

    override fun onPause() {
        super.onPause()
        disposeBag.dispose()
    }

    override fun onResume() {
        super.onResume()
        getCompanies()
    }

    fun onCompanyPressed(company: CompanyUI) {
        activityNavigator.goToCompanyDetail(company.id)
    }
}