package com.xavisson.ninetyninechallenge.presentation.companydetail.injector

import android.support.v7.app.AppCompatActivity
import com.xavisson.ninetyninechallenge.domain.company.*
import com.xavisson.ninetyninechallenge.domain.executor.ThreadScheduler
import com.xavisson.ninetyninechallenge.injection.PerActivity
import com.xavisson.ninetyninechallenge.injection.components.ActivityComponent
import com.xavisson.ninetyninechallenge.injection.components.ApplicationComponent
import com.xavisson.ninetyninechallenge.injection.modules.ActivityModule
import com.xavisson.ninetyninechallenge.presentation.companydetail.CompanyDetailActivity
import com.xavisson.ninetyninechallenge.presentation.companydetail.CompanyDetailPresenter
import com.xavisson.ninetyninechallenge.presentation.navigator.ActivityNavigator
import com.xavisson.ninetyninechallenge.presentation.navigator.ApplicationActivityNavigator
import dagger.Component
import dagger.Module
import dagger.Provides

@Module
class CompanyDetailModule(private val activity: AppCompatActivity) : ActivityModule {

    @Provides
    fun providesCompanyDetailPresenter(
            getCompanyDetailsUseCase: GetCompanyDetailsUseCase,
            subscribeToSharePriceUpdatesUseCase: SubscribeToSharePriceUpdatesUseCase,
            activityNavigator: ActivityNavigator
    ): CompanyDetailPresenter {
        return CompanyDetailPresenter(
                getCompanyDetailsUseCase = getCompanyDetailsUseCase,
                subscribeToSharePriceUpdatesUseCase = subscribeToSharePriceUpdatesUseCase,
                activityNavigator = activityNavigator
        )
    }

    @Provides
    fun provideGetCompanyDetailsUseCase(
            companyResource: CompanyResource,
            threadScheduler: ThreadScheduler
    ): GetCompanyDetailsUseCase {
        return GetCompanyDetails(
                companyResource = companyResource,
                threadScheduler = threadScheduler
        )
    }

    @Provides
    fun provideSubscribeToSharePriceUpdatesUseCase(
            companyResource: CompanyResource,
            threadScheduler: ThreadScheduler
    ): SubscribeToSharePriceUpdatesUseCase {
        return SubscribeToSharePriceUpdates(
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
        modules = arrayOf(CompanyDetailModule::class)
)
interface CompanyDetailComponent : ActivityComponent {
    fun inject(activity: CompanyDetailActivity)
}