package com.xavisson.ninetyninechallenge.presentation.companylist

import com.xavisson.ninetyninechallenge.base.BaseView

interface CompanyListView : BaseView {
    fun fillCompanyData()
}

interface CompanyUI

data class CompanyItemUI(
        val id: Int,
        val name: String,
        val ric: String,
        val sharePrice: Double
)