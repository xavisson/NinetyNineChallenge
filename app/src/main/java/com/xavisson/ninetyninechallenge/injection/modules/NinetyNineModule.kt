package com.xavisson.ninetyninechallenge.injection.modules

import com.xavisson.ninetyninechallenge.data.company.CompanyApiClient
import com.xavisson.ninetyninechallenge.domain.company.CompanyApi
import dagger.Module
import dagger.Provides

@Module
class NinetyNineModule {
    @Provides
    fun provideCompanyApi(): CompanyApi {
        return CompanyApiClient()
    }
}