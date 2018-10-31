package com.xavisson.ninetyninechallenge.injection.modules

import com.xavisson.ninetyninechallenge.domain.company.CompanyListStream
import com.xavisson.ninetyninechallenge.domain.company.CompanyResource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
open class CompanyModule {

    companion object {
        private val REPO_VERSION: Int = 1
    }

    @Provides
    @Singleton
    fun providesServiceListStream(): CompanyListStream {
        return CompanyListStream()
    }

    @Provides
    @Singleton
    fun provideCompanyResource(
            modelStream: CompanyListStream
    ): CompanyResource {
        return CompanyResource(
                modelStream = modelStream
        )
    }
}