package com.xavisson.ninetyninechallenge.domain.company

import com.xavisson.ninetyninechallenge.domain.reactive.Stream
import io.reactivex.subjects.BehaviorSubject


class CompanyListStream : Stream<List<Company>>(BehaviorSubject.create())

class CompanyResource(
        private val modelStream: CompanyListStream
) {
    val stream get() = modelStream
}