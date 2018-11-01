package com.xavisson.ninetyninechallenge.injection.modules

import com.xavisson.ninetyninechallenge.domain.company.CompanyApi
import com.xavisson.ninetyninechallenge.domain.company.CompanyListStream
import com.xavisson.ninetyninechallenge.domain.company.CompanyResource
import com.xavisson.ninetyninechallenge.domain.executor.ThreadExecutor
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = arrayOf(NinetyNineModule::class))
open class CompanyModule {

    @Provides
    @Singleton
    fun providesServiceListStream(): CompanyListStream {
        return CompanyListStream()
    }

    @Provides
    @Singleton
    fun provideCompanyResource(
            modelStream: CompanyListStream,
            companyApi: CompanyApi,
            threadExecutor: ThreadExecutor
    ): CompanyResource {
        return CompanyResource(
                modelStream = modelStream,
                companyApi = companyApi,
                threadExecutor = threadExecutor
        )
    }
}