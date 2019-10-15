package by.minsk.polina_pasevina.cryptocurrency.presentation.common

import android.support.annotation.CallSuper

abstract class BaseMvpPresenter<S, V : MvpView<S>>(initialState: S) : MvpPresenter<V> {

    protected var view: V? = null
        private set

    protected var state: S = initialState
    set(value) {
        field = value
        renderState()
    }

    override fun attachView(view: V) {
        this.view = view
        renderState()
    }

    override fun detachView() {
        this.view = null
    }

    private fun isViewAttached() = view != null

    private fun renderState() {
        view?.render(state)
    }

    @CallSuper
    override fun onDestroy() {

    }
}