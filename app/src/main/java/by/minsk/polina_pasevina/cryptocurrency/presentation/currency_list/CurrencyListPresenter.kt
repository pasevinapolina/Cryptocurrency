package by.minsk.polina_pasevina.cryptocurrency.presentation.currency_list

import by.minsk.polina_pasevina.cryptocurrency.presentation.common.MvpPresenter

interface CurrencyListPresenter : MvpPresenter<CurrencyListView> {
    fun onCurrencyClicked(id: String)
}