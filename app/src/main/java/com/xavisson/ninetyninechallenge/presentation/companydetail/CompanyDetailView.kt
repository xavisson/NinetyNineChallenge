package com.xavisson.ninetyninechallenge.presentation.companydetail

import com.xavisson.ninetyninechallenge.base.BaseView
import com.xavisson.ninetyninechallenge.domain.company.Company
import com.xavisson.ninetyninechallenge.utils.formatPrice

interface CompanyDetailView : BaseView {
    fun showCompanyDetails(companyDetails: CompanyDetailUI)
    fun refreshSharePrice(sharePrice: String)
}

data class CompanyDetailUI(
        val id: Int,
        val name: String,
        val ric: String,
        val sharePrice: String,
        val description: String,
        val country: String
)

fun Company.toDetailUi(): CompanyDetailUI {
    return CompanyDetailUI(
            id = id,
            name = name,
            ric = ric,
            sharePrice = sharePrice.formatPrice(),
            description = description ?: "",
            country = country ?: ""
    )
}