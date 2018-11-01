package com.xavisson.ninetyninechallenge.presentation.navigator

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import com.xavisson.ninetyninechallenge.presentation.companydetail.CompanyDetailActivity
import com.xavisson.ninetyninechallenge.presentation.navigator.IntentExtras.COMPANY_ID
import java.lang.ref.WeakReference

interface ActivityNavigator {
    fun goToCompanyDetail(companyId: Int)
    fun closeDetail()
}

class ApplicationActivityNavigator(
        activity: AppCompatActivity
) : ActivityNavigator {

    private val activityRef = WeakReference(activity)
    private val activity: AppCompatActivity?
        get() = activityRef.get()

    override fun goToCompanyDetail(companyId: Int) {
        val intent = Intent(activity, CompanyDetailActivity::class.java)
        intent.putExtra(COMPANY_ID, companyId)
        activity?.startActivity(intent)
    }

    override fun closeDetail() {
        activity?.onBackPressed()
    }
}

object IntentExtras {
    const val COMPANY_ID = "COMPANY_ID"
}