package com.xavisson.ninetyninechallenge.presentation.companylist

import com.xavisson.ninetyninechallenge.NinetyNineChallengeApplication
import com.xavisson.ninetyninechallenge.R
import com.xavisson.ninetyninechallenge.base.BaseActivity
import com.xavisson.ninetyninechallenge.lifecycle.Presenter
import com.xavisson.ninetyninechallenge.presentation.companylist.injector.CompanyListModule
import com.xavisson.ninetyninechallenge.presentation.companylist.injector.DaggerCompanyListComponent
import javax.inject.Inject

class CompanyListActivity : BaseActivity(), CompanyListView {

    override val layoutRes: Int = R.layout.companylist_layout

    @Presenter
    @Inject
    lateinit var presenter: CompanyListPresenter

    override fun initInjector() {
        DaggerCompanyListComponent.builder()
                .applicationComponent(NinetyNineChallengeApplication.applicationComponent)
                .companyListModule(CompanyListModule(this))
                .build()
                .inject(this)
    }
}