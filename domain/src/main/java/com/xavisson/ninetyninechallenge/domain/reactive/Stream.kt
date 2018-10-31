package com.xavisson.ninetyninechallenge.domain.reactive

import io.reactivex.Observable
import io.reactivex.subjects.Subject

open class Stream<SUBJECT>(private val subject: Subject<SUBJECT>) {
    fun publish(obj: SUBJECT) {
        subject.onNext(obj)
    }

    fun getObservable(): Observable<SUBJECT> {
        return subject
    }

    fun hasObservers(): Boolean {
        return subject.hasObservers()
    }
}