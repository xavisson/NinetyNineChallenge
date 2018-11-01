package com.xavisson.ninetyninechallenge.presentation.companydetail

import com.xavisson.ninetyninechallenge.base.BasePresenter
import com.xavisson.ninetyninechallenge.domain.company.GetCompanyDetailsUseCase
import com.xavisson.ninetyninechallenge.domain.company.SubscribeToSharePriceUpdatesUseCase
import com.xavisson.ninetyninechallenge.domain.reactive.addDisposableTo
import com.xavisson.ninetyninechallenge.presentation.navigator.ActivityNavigator
import io.reactivex.rxkotlin.subscribeBy

class CompanyDetailPresenter(
        private val getCompanyDetailsUseCase: GetCompanyDetailsUseCase,
        private val subscribeToSharePriceUpdatesUseCase: SubscribeToSharePriceUpdatesUseCase,
        private val activityNavigator: ActivityNavigator
) : BasePresenter<CompanyDetailView>() {

    var companyId: Int? = null
        set(value) {
            field = value
            field?.let {
                getCompanyDetails()
                subscribeToSharePriceUpdates()
            }
        }

    fun getCompanyDetails() {
        getCompanyDetailsUseCase.execute(companyId!!)
                .subscribeBy(
                        onNext = { getView()?.showCompanyDetails(it.toDetailUi()) }
                ).addDisposableTo(disposeBag)
    }

    private fun subscribeToSharePriceUpdates() {
        subscribeToSharePriceUpdatesUseCase.execute(companyId!!)
                .subscribeBy(
                        onNext = { getView()?.refreshSharePrice(it)
                        }
                ).addDisposableTo(disposeBag)
    }

    fun onBackPressed() {
        activityNavigator.closeDetail()
    }

    companion object {
        const val COMPANY_ID_LOST = -1
    }
}