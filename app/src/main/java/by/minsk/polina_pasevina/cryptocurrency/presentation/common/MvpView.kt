package by.minsk.polina_pasevina.cryptocurrency.presentation.common

interface MvpView<S> {
    fun render(state: S)
}