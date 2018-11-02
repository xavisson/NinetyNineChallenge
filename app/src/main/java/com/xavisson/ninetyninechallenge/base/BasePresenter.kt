package com.xavisson.ninetyninechallenge.base

import com.xavisson.ninetyninechallenge.domain.logger.Logger
import com.xavisson.ninetyninechallenge.domain.reactive.DisposeBag
import java.lang.ref.WeakReference

abstract class BasePresenter<T : BaseView> {

    private var view: WeakReference<T>? = null
    var disposeBag: DisposeBag = DisposeBag()

    fun setView(view: T) {
        this.view = WeakReference(view)
        Logger.d {
            "[LIFE] BasePresenter::setView called in ${this::class.java.simpleName}"
        }
    }

    fun getView(): T? = view?.get()

    fun clearDisposeBag() {
        disposeBag.dispose()
    }

    open fun onCreate() {}

    open fun onFirstLayout() {}

    open fun onResume() {
        Logger.d {
            "[LIFE] BasePresenter::onResume called in ${this::class.java.simpleName}"
        }
    }

    open fun onPause() {
        Logger.d {
            "[LIFE] BasePresenter::onPause called in ${this::class.java.simpleName}"
        }
    }

    open fun onDestroy() {
        clearDisposeBag()
    }

    open fun viewWillAppear() {
        Logger.d {
            "[LIFE] BasePresenter::viewWillAppear called in ${this::class.java.simpleName}"
        }
    }

    open fun viewWillDisappear() {
        Logger.d {
            "[LIFE] BasePresenter::viewWillDisappear called in ${this::class.java.simpleName}"
        }
        view = null
    }

    open fun handleUnexpectedOnDestroy() {
        Logger.w {
            "[LIFE] BasePresenter::handleUnexpectedOnDestroy called in ${this::class.java.simpleName}"
        }
    }
}
