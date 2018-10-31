package com.xavisson.ninetyninechallenge.presentation.companylist

import android.os.Handler
import com.xavisson.ninetyninechallenge.base.BasePresenter
import java.util.*

class CompanyListPresenter : BasePresenter<CompanyListView>() {

    val handler = Handler()
    val runnable = { getView()?.fillCompanyData(); scheduleReload() }

    private val items = (1..10).map { CompanyItemUI(
            id = it,
            name = "company name $it",
            ric = "ric $it/",
            sharePrice = it.toDouble()) }

    override fun onCreate() {
        super.onCreate()
        runnable()
    }
    fun generate(): List<CompanyItemUI> {
        val rand = Random(System.currentTimeMillis())
        return items.filter { rand.nextBoolean() }
    }

    private fun scheduleReload() {
        handler.postDelayed(runnable, 4000)
    }
}