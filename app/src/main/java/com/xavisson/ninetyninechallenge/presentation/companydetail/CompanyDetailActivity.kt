package com.xavisson.ninetyninechallenge.presentation.companydetail

import android.support.v4.content.ContextCompat
import com.xavisson.ninetyninechallenge.NinetyNineChallengeApplication
import com.xavisson.ninetyninechallenge.R
import com.xavisson.ninetyninechallenge.base.BaseActivity
import com.xavisson.ninetyninechallenge.lifecycle.Presenter
import com.xavisson.ninetyninechallenge.presentation.companydetail.CompanyDetailPresenter.Companion.COMPANY_ID_LOST
import com.xavisson.ninetyninechallenge.presentation.companydetail.injector.CompanyDetailModule
import com.xavisson.ninetyninechallenge.presentation.companydetail.injector.DaggerCompanyDetailComponent
import com.xavisson.ninetyninechallenge.presentation.navigator.IntentExtras
import kotlinx.android.synthetic.main.companydetail_layout.*
import javax.inject.Inject

class CompanyDetailActivity : BaseActivity(), CompanyDetailView {

    override val layoutRes: Int = R.layout.companydetail_layout

    @Presenter
    @Inject
    lateinit var presenter: CompanyDetailPresenter

    override fun initInjector() {
        DaggerCompanyDetailComponent.builder()
                .applicationComponent(NinetyNineChallengeApplication.applicationComponent)
                .companyDetailModule(CompanyDetailModule(this))
                .build()
                .inject(this)
    }

    override fun setupViews() {
        presenter.companyId = intent.getIntExtra(IntentExtras.COMPANY_ID, COMPANY_ID_LOST)
        setupToolbar()
    }

    override fun showCompanyDetails(companyDetails: CompanyDetailUI) {
        name.text = companyDetails.name
        ric.text = companyDetails.ric
        description.text = companyDetails.description
        country.text = companyDetails.country
        sharePrice.text = companyDetails.sharePrice.toString()
    }

    override fun refreshSharedPrice(sharePriceValue: Double) {
        sharePrice.text = sharePriceValue.toString()
    }

    private fun setupToolbar() {
        toolbar.navigationIcon = ContextCompat.getDrawable(this, R.drawable.ic_back_arrow)
        toolbar.setNavigationOnClickListener { presenter.onBackPressed() }
    }
}