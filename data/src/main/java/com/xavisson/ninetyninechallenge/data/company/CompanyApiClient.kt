package com.xavisson.ninetyninechallenge.data.company

import com.xavisson.ninetyninechallenge.data.client.ApiClientBuilder
import com.xavisson.ninetyninechallenge.data.client.NinetyNineApiClient
import com.xavisson.ninetyninechallenge.domain.company.Company
import com.xavisson.ninetyninechallenge.domain.company.CompanyApi
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

class CompanyApiClient : CompanyApi {

    override fun searchCompanies(): Observable<List<Company>> {
        val companyApiClient = ApiClientBuilder(NinetyNineApiClient(emptySet()))
                .buildEndpoint(CompanyApiDefinition::class)

        return companyApiClient.getCompanies().map { it.map { it.toDomain() } }
    }

    override fun searchCompanyById(companyId: Int): Observable<Company> {
        val companyApiClient = ApiClientBuilder(NinetyNineApiClient(emptySet()))
                .buildEndpoint(CompanyApiDefinition::class)

        return companyApiClient.getCompanyById(companyId).map { it.toDomain() }
    }
}

interface CompanyApiDefinition {

    @GET("/testapi/1/companies")
    fun getCompanies(): Observable<List<CompanyApiModel>>

    @GET("/testapi/1/companies/{companyId}")
    fun getCompanyById(
            @Path("companyId") companyId: Int
    ): Observable<CompanyApiModel>
}