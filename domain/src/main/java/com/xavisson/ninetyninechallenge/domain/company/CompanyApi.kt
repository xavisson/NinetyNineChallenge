package com.xavisson.ninetyninechallenge.domain.company

import io.reactivex.Observable

interface CompanyApi {
    fun searchCompanies(): Observable<List<Company>>
}