package by.minsk.polina_pasevina.cryptocurrency.presentation.currency_list

import by.minsk.polina_pasevina.cryptocurrency.interactors.CryptocurrencyContract
import by.minsk.polina_pasevina.cryptocurrency.interactors.GetCryptocurrenciesContract
import by.minsk.polina_pasevina.cryptocurrency.interactors.GetCryptocurrenciesInteractor
import by.minsk.polina_pasevina.cryptocurrency.presentation.common.BaseMvpPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposables
import io.reactivex.schedulers.Schedulers

class CurrencyListPresenterImpl(
    private val getCryptocurrenciesInteractor: GetCryptocurrenciesInteractor
) : BaseMvpPresenter<CurrencyListViewState, CurrencyListView>(CurrencyListViewState.INITIAL),
    CurrencyListPresenter {

    private var disposable = Disposables.disposed()

    init {
        disposable = getCryptocurrenciesInteractor.get()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { contract ->
                val currencies = when (contract) {
                    is GetCryptocurrenciesContract.Success -> contract.currencies.map(contractToViewState)
                    is GetCryptocurrenciesContract.Failed -> emptyList()
                }
                CurrencyListViewState(false, currencies)
            }.subscribe { s -> state = s }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }

    override fun onCurrencyClicked(id: Int) {
        // TODO: open details screen
    }

    private val contractToViewState = { contract: CryptocurrencyContract ->
        CurrencyViewState(
            id = contract.id,
            name = contract.name,
            price = contract.usdPrice,
            imageUrl = contract.imageUrl
        )
    }
}