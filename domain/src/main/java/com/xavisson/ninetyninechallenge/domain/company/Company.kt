package com.xavisson.ninetyninechallenge.domain.company

data class Company(
        val id: Int,
        val name: String,
        val ric: String,
        val sharePrice: Double,
        val description: String?,
        val country: String?
)

