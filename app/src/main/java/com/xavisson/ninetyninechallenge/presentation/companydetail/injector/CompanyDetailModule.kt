package com.xavisson.ninetyninechallenge.presentation.companydetail.injector

import android.support.v7.app.AppCompatActivity
import com.xavisson.ninetyninechallenge.injection.PerActivity
import com.xavisson.ninetyninechallenge.injection.components.ActivityComponent
import com.xavisson.ninetyninechallenge.injection.components.ApplicationComponent
import com.xavisson.ninetyninechallenge.injection.modules.ActivityModule
import com.xavisson.ninetyninechallenge.presentation.companydetail.CompanyDetailActivity
import com.xavisson.ninetyninechallenge.presentation.companydetail.CompanyDetailPresenter
import dagger.Component
import dagger.Module
import dagger.Provides

@Module
class CompanyDetailModule(private val activity: AppCompatActivity) : ActivityModule {

    @Provides
    fun providesCompanyDetailPresenter(): CompanyDetailPresenter {
        return CompanyDetailPresenter()
    }
}

@PerActivity
@Component(
        dependencies = arrayOf(ApplicationComponent::class),
        modules = arrayOf(CompanyDetailModule::class)
)
interface CompanyDetailComponent : ActivityComponent {
    fun inject(activity: CompanyDetailActivity)
}