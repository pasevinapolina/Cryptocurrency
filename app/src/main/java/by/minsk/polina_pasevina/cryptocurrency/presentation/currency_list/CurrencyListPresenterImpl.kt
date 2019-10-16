package by.minsk.polina_pasevina.cryptocurrency.presentation.currency_list

import by.minsk.polina_pasevina.cryptocurrency.interactors.CryptocurrencyContract
import by.minsk.polina_pasevina.cryptocurrency.interactors.GetCryptocurrenciesContract
import by.minsk.polina_pasevina.cryptocurrency.interactors.GetCryptoCurrenciesInteractor
import by.minsk.polina_pasevina.cryptocurrency.presentation.common.BaseMvpPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposables
import io.reactivex.schedulers.Schedulers

class CurrencyListPresenterImpl(
    getCryptoCurrenciesInteractor: GetCryptoCurrenciesInteractor
) : BaseMvpPresenter<CurrencyListViewState, CurrencyListView>(CurrencyListViewState.INITIAL),
    CurrencyListPresenter {

    private var disposable = Disposables.disposed()

    init {
        disposable = getCryptoCurrenciesInteractor.get()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { contract ->
                when (contract) {
                    is GetCryptocurrenciesContract.Success ->
                        CurrencyListViewState(
                            loading = false,
                            currencies = contract.currencies.map(contractToViewState),
                            showError = false
                        )
                    is GetCryptocurrenciesContract.Failed ->
                        CurrencyListViewState(loading = false, currencies = emptyList(), showError = true)
                }
            }
            .subscribe { s -> state = s }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }

    override fun onCurrencyClicked(id: Long) {
        navigate { v -> v.openCurrencyDetailsScreen(id) }
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