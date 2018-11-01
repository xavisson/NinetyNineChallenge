package com.xavisson.ninetyninechallenge.presentation.companydetail

import com.xavisson.ninetyninechallenge.base.BasePresenter

class CompanyDetailPresenter : BasePresenter<CompanyDetailView>() {

    var companyId: Int? = null

    companion object {
        const val COMPANY_ID_LOST = -1
    }
}