package com.xavisson.ninetyninechallenge.presentation.companydetail

import com.xavisson.ninetyninechallenge.base.BaseView
import com.xavisson.ninetyninechallenge.domain.company.Company

interface CompanyDetailView : BaseView {
    fun showCompanyDetails(companyDetails: CompanyDetailUI)
    fun refreshSharedPrice(sharePrice: Double)
}

data class CompanyDetailUI(
        val id: Int,
        val name: String,
        val ric: String,
        val sharePrice: Double,
        val description: String,
        val country: String
)

fun Company.toDetailUi(): CompanyDetailUI {
    return CompanyDetailUI(
            id = id,
            name = name,
            ric = ric,
            sharePrice = sharePrice,
            description = description ?: "",
            country = country ?: ""
    )
}