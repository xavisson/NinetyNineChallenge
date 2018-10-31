package com.xavisson.ninetyninechallenge.presentation.companylist

import android.os.Bundle
import android.os.Handler
import android.support.v7.widget.LinearLayoutManager
import com.xavisson.ninetyninechallenge.NinetyNineChallengeApplication
import com.xavisson.ninetyninechallenge.R
import com.xavisson.ninetyninechallenge.base.BaseActivity
import com.xavisson.ninetyninechallenge.lifecycle.Presenter
import com.xavisson.ninetyninechallenge.presentation.companylist.adapter.CompanyAdapter
import com.xavisson.ninetyninechallenge.presentation.companylist.injector.CompanyListModule
import com.xavisson.ninetyninechallenge.presentation.companylist.injector.DaggerCompanyListComponent
import kotlinx.android.synthetic.main.companylist_layout.*
import javax.inject.Inject

class CompanyListActivity : BaseActivity(), CompanyListView {

    override val layoutRes: Int = R.layout.companylist_layout

    private val adapter = CompanyAdapter()

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

    override fun setupViews() {
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        companyList.layoutManager = LinearLayoutManager(this)
        companyList.adapter = adapter
    }

    override fun fillCompanyData() {
        adapter.items = presenter.generate()
    }
}