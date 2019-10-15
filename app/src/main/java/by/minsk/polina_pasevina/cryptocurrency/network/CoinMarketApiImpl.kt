package by.minsk.polina_pasevina.cryptocurrency.network

import io.reactivex.Observable

class CoinMarketApiImpl(private val clientFactory: CoinMarketClientFactory) : CoinMarketApi {

    override fun getCurrencyList(): Observable<RequestState<CryptocurrencyResponse>> {
        return clientFactory.makeClient()
            .getCurrencyList()
            .map { response -> RequestState.wrap(response) }
            .onErrorReturn {
                RequestState(
                    status = RequestStatus.FAILED,
                    error = ConnectionFailedException(),
                    data = null
                )
            }
    }
}