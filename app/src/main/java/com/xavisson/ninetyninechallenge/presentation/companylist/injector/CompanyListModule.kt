package com.xavisson.ninetyninechallenge.presentation.companylist.injector

import android.app.Activity
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
    fun provideCompanyListPresenter(): CompanyListPresenter {
        return CompanyListPresenter()
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