package com.xavisson.ninetyninechallenge.presentation.companylist.injector

import android.app.Activity
import com.xavisson.ninetyninechallenge.domain.company.CompanyResource
import com.xavisson.ninetyninechallenge.domain.company.SubscribeToCompanyListUpdates
import com.xavisson.ninetyninechallenge.domain.company.SubscribeToCompanyListUpdatesUseCase
import com.xavisson.ninetyninechallenge.domain.executor.ThreadScheduler
import com.xavisson.ninetyninechallenge.injection.PerActivity
import com.xavisson.ninetyninechallenge.injection.components.ActivityComponent
import com.xavisson.ninetyninechallenge.injection.components.ApplicationComponent
import com.xavisson.ninetyninechallenge.injection.modules.ActivityModule
import com.xavisson.ninetyninechallenge.presentation.companylist.CompanyListActivity
import com.xavisson.ninetyninechallenge.presentation.companylist.CompanyListPresenter
import dagger.Component
import dagger.Module
import dagger.Provides

@Module
class CompanyListModule(activity: Activity) : ActivityModule {

    @Provides
    fun provideCompanyListPresenter(
            subscribeToCompanyListUpdatesUseCase: SubscribeToCompanyListUpdatesUseCase
    ): CompanyListPresenter {
        return CompanyListPresenter(
                subscribeToCompanyListUpdatesUseCase
        )
    }

    @Provides
    fun provideSubscribeToCompanyListUpdatesUseCase(
            companyResource: CompanyResource,
            threadScheduler: ThreadScheduler
    ): SubscribeToCompanyListUpdatesUseCase {
        return SubscribeToCompanyListUpdates(
                companyResource = companyResource,
                threadScheduler = threadScheduler
        )
    }

}

@PerActivity
@Component(
        dependencies = arrayOf(ApplicationComponent::class),
        modules = arrayOf(CompanyListModule::class)
)
interface CompanyListComponent : ActivityComponent {
    fun inject(activity: CompanyListActivity)
}