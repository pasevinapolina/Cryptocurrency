package by.minsk.polina_pasevina.cryptocurrency.interactors

import by.minsk.polina_pasevina.cryptocurrency.network.CoinMarketApi
import io.reactivex.Observable

class GetCryptocurrenciesInteractorImpl(
    private val coinMarketApi: CoinMarketApi
) : GetCryptocurrenciesInteractor {

    override fun get(): Observable<GetCryptocurrenciesContract> {
        return Observable.just(
            GetCryptocurrenciesContract.Success(listOf(
                CryptocurrencyContract(1, "Bitcoin", 4887.89f, ""),
                CryptocurrencyContract(2, "Bitcoin", 5555.89f, ""),
                CryptocurrencyContract(3, "Bitcoin", 6778.89f, "")
            ))
        )
    }
}