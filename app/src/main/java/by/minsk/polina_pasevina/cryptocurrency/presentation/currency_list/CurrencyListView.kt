package by.minsk.polina_pasevina.cryptocurrency.presentation.currency_list

import by.minsk.polina_pasevina.cryptocurrency.presentation.common.MvpView

interface CurrencyListView : MvpView<CurrencyListViewState> {
    fun openCurrencyDetailsScreen(id: String)
}