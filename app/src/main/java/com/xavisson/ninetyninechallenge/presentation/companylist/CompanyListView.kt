package com.xavisson.ninetyninechallenge.presentation.companylist

import com.xavisson.ninetyninechallenge.base.BaseView
import com.xavisson.ninetyninechallenge.domain.company.Company
import com.xavisson.ninetyninechallenge.utils.formatPrice

interface CompanyListView : BaseView {
    fun showCompanyData(companies: List<CompanyUI>)
    fun showErrorMessage()
}

data class CompanyUI(
        val id: Int,
        val name: String,
        val ric: String,
        val sharePrice: String
)

fun List<Company>.toUi(): List<CompanyUI> {
    return this.map { it.toUi() }
}

fun Company.toUi(): CompanyUI {
    return CompanyUI(
            id = id,
            name = name,
            ric = ric,
            sharePrice = sharePrice.formatPrice()
    )
}