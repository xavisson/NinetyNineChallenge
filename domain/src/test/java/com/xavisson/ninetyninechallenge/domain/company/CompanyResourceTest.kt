package com.xavisson.ninetyninechallenge.domain.company

import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.xavisson.ninetyninechallenge.domain.company.CompanyResource.Companion.POLLING_INTERVAL_IN_SECONDS
import com.xavisson.ninetyninechallenge.domain.executor.ThreadExecutor
import io.reactivex.Observable
import io.valeet.driver.domain.executor.TestSchedulerRule
import io.valeet.driver.domain.executor.ThreadExecutorForTest
import org.amshove.kluent.`should be less than`
import org.amshove.kluent.mock
import org.amshove.kluent.shouldBe
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.TimeUnit

class CompanyResourceTest {

    private lateinit var companyResource: CompanyResource
    private lateinit var companyApi: CompanyApi
    private lateinit var threadExecutorForTest: ThreadExecutor
    private lateinit var modelStream: CompanyListStream
    private val companyMother = CompanyMother()

    @Rule
    @JvmField
    var testSchedulerRule: TestSchedulerRule = TestSchedulerRule()

    @Before
    fun setUp() {
        threadExecutorForTest = ThreadExecutorForTest()
        modelStream = CompanyListStream()
        companyApi = mock()

        companyResource = CompanyResource(
                modelStream = modelStream,
                threadExecutor = threadExecutorForTest,
                companyApi = companyApi
                )
    }

    @Test
    fun shouldOutputListHaveShameSizeAsInputList() {
        val anyCompanyList: List<Company> = companyMother.givenListOfCompanies()
        whenever(companyApi.searchCompanies()).thenReturn(Observable.just(anyCompanyList))

        waitUntilRequestIsProcessed()

        val subscription = modelStream.getObservable().test()
        val list = subscription.values().last()

        list.size shouldBe anyCompanyList.size
    }

    @Test
    fun shouldOrderListBasedOnSharePrice() {
        val unorderedCompanyList: List<Company> = companyMother.givenUnorderedListOfCompanies()
        whenever(companyApi.searchCompanies()).thenReturn(Observable.just(unorderedCompanyList))

        waitUntilRequestIsProcessed()

        val subscription = modelStream.getObservable().test()
        val list = subscription.values().last()

        list[0].sharePrice `should be less than` list[1].sharePrice
    }

    @Test
    fun shouldPollAtGivenPace() {
        val companyList = companyMother.givenListOfCompanies()
        whenever(companyApi.searchCompanies()).thenReturn(Observable.just(companyList))

        waitUntilRequestIsProcessed()
        waitUntilRequestIsProcessed()

        verify(companyApi, times(3)).searchCompanies()
    }

    fun waitUntilRequestIsProcessed() {
        testSchedulerRule.getTestScheduler().advanceTimeBy(POLLING_INTERVAL_IN_SECONDS, TimeUnit.SECONDS)
    }
}