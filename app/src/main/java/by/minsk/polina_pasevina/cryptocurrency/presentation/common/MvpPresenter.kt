package by.minsk.polina_pasevina.cryptocurrency.presentation.common

interface MvpPresenter<V : MvpView<*>> {

    fun attachView(view: V)

    fun detachView()

    fun onDestroy()
}