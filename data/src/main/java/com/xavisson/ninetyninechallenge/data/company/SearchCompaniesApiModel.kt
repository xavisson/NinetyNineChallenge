package com.xavisson.ninetyninechallenge.data.company

import com.google.gson.annotations.SerializedName
import com.xavisson.ninetyninechallenge.domain.company.Company

data class SearchCompaniesApiModel(
        val results: List<CompanyApiModel>
)

data class CompanyApiModel(
        @SerializedName("id") val id: Int,
        @SerializedName("name") val name: String,
        @SerializedName("ric") val ric: String,
        @SerializedName("sharePrice") val sharePrice: Double,
        @SerializedName("description") val description: String?,
        @SerializedName("country") val country: String?
)

fun SearchCompaniesApiModel.toDomain(): List<Company> {
    return results.map { it.toDomain() }
}

fun CompanyApiModel.toDomain(): Company {
    return Company(
            id = id,
            name = name,
            ric = ric,
            sharePrice = sharePrice
    )
}