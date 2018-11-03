package com.xavisson.ninetyninechallenge.domain.company

import com.xavisson.ninetyninechallenge.domain.utils.random

class CompanyMother {
    companion object {
        private val anyId = (1 until 10).random()
        private val anySharePrice = (1 until 10000).random().toDouble()
        private const val anyName = "any_name"
        private const val anyRIC = "any_ric"
        private const val anyDescription = "any_description"
        private const val anyCountry = "any_country"
    }

    fun givenAnyCompany(): Company {
        return givenACompany()
    }

    fun givenACompany(
            id: Int = anyId,
            sharedPrice: Double = anySharePrice,
            name: String = anyName,
            ric: String = anyRIC,
            description: String = anyDescription,
            country: String = anyCountry
    ): Company {
        return Company(
                id = anyId,
                sharePrice = anySharePrice,
                name = anyName,
                ric = anyRIC,
                description = anyDescription,
                country = anyCountry
        )
    }

    fun givenACompanyWithSharePrice(
            sharedPrice: Double
    ): Company {
        return Company(
                id = anyId,
                sharePrice = sharedPrice,
                name = anyName,
                ric = anyRIC,
                description = anyDescription,
                country = anyCountry
        )
    }

    fun givenListOfCompanies(): List<Company> {
        return listOf(
                givenAnyCompany(),
                givenAnyCompany()
        )
    }

    fun givenUnorderedListOfCompanies(): List<Company> {
        return listOf(
                givenACompanyWithSharePrice(sharedPrice = 7642.0),
                givenACompanyWithSharePrice(sharedPrice = 9564.0),
                givenACompanyWithSharePrice(sharedPrice = 1635.0)
        )
    }
}

