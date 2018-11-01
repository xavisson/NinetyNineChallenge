package com.xavisson.ninetyninechallenge.presentation.companydetail

import com.xavisson.ninetyninechallenge.base.BasePresenter
import com.xavisson.ninetyninechallenge.domain.company.GetCompanyDetailsUseCase
import com.xavisson.ninetyninechallenge.domain.logger.Logger
import io.reactivex.rxkotlin.subscribeBy

class CompanyDetailPresenter(
    private val getCompanyDetailsUseCase: GetCompanyDetailsUseCase
) : BasePresenter<CompanyDetailView>() {

    var companyId: Int? = null
    set(value) {
        field = value
        field?.let { getCompanyDetails() }
    }

    fun getCompanyDetails() {
        getCompanyDetailsUseCase.execute(companyId!!).subscribeBy(
                onNext = { Logger.d { "xtest: ${it.name}" } }
        )
    }

    companion object {
        const val COMPANY_ID_LOST = -1
    }
}