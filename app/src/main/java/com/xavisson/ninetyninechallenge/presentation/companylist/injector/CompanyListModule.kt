package com.xavisson.ninetyninechallenge.presentation.companylist.injector

import android.support.v7.app.AppCompatActivity
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
import com.xavisson.ninetyninechallenge.presentation.navigator.ActivityNavigator
import com.xavisson.ninetyninechallenge.presentation.navigator.ApplicationActivityNavigator
import dagger.Component
import dagger.Module
import dagger.Provides

@Module
class CompanyListModule(private val activity: AppCompatActivity) : ActivityModule {

    @Provides
    fun provideCompanyListPresenter(
            subscribeToCompanyListUpdatesUseCase: SubscribeToCompanyListUpdatesUseCase,
            activityNavigator: ActivityNavigator
    ): CompanyListPresenter {
        return CompanyListPresenter(
                subscribeToCompanyListUpdatesUseCase = subscribeToCompanyListUpdatesUseCase,
                activityNavigator = activityNavigator
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

    @Provides
    fun provideActivityNavigator(): ActivityNavigator = ApplicationActivityNavigator(
            activity
    )

}

@PerActivity
@Component(
        dependencies = arrayOf(ApplicationComponent::class),
        modules = arrayOf(CompanyListModule::class)
)
interface CompanyListComponent : ActivityComponent {
    fun inject(activity: CompanyListActivity)
}