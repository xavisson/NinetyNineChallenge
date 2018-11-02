package com.xavisson.ninetyninechallenge.presentation.companylist

import android.support.v7.widget.LinearLayoutManager
import com.xavisson.ninetyninechallenge.NinetyNineChallengeApplication
import com.xavisson.ninetyninechallenge.R
import com.xavisson.ninetyninechallenge.base.BaseActivity
import com.xavisson.ninetyninechallenge.domain.logger.Logger
import com.xavisson.ninetyninechallenge.lifecycle.Presenter
import com.xavisson.ninetyninechallenge.presentation.companylist.adapter.CompanyAdapter
import com.xavisson.ninetyninechallenge.presentation.companylist.injector.CompanyListModule
import com.xavisson.ninetyninechallenge.presentation.companylist.injector.DaggerCompanyListComponent
import com.xavisson.ninetyninechallenge.utils.gone
import com.xavisson.ninetyninechallenge.utils.visible
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
        refreshButton.setOnClickListener { presenter.getCompanies() }
    }

    private fun setupRecyclerView() {
        companyList.layoutManager = LinearLayoutManager(this)
        companyList.adapter = adapter
        adapter.onItemClick = { company -> presenter.onCompanyPressed(company) }
    }

    override fun showCompanyData(companies: List<CompanyUI>) {
        errorMessageLayout.gone()
        companyList.visible()
        adapter.items = companies
    }

    override fun showErrorMessage() {
        errorMessageLayout.visible()
        companyList.gone()
    }
}