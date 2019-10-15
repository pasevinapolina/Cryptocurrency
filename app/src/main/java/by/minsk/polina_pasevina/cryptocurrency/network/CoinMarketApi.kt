package by.minsk.polina_pasevina.cryptocurrency.network

import io.reactivex.Observable

interface CoinMarketApi {
    fun getCurrencyList(): Observable<RequestState<CryptocurrencyResponse>>
}