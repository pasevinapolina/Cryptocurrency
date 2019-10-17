package by.minsk.polina_pasevina.cryptocurrency.network

import by.minsk.polina_pasevina.cryptocurrency.entities.QuoteType
import by.minsk.polina_pasevina.cryptocurrency.gateways.network.CoinMarketApi
import by.minsk.polina_pasevina.cryptocurrency.network.request.RequestState
import by.minsk.polina_pasevina.cryptocurrency.network.response.CurrencyInfoResponse
import by.minsk.polina_pasevina.cryptocurrency.network.response.CryptoCurrencyResponse
import io.reactivex.Observable

class CoinMarketApiImpl(private val clientFactory: CoinMarketClientFactory) :
    CoinMarketApi {

    override fun getCurrencyList(
        quotes: List<QuoteType>
    ): Observable<RequestState<List<CryptoCurrencyResponse>>> {
        val convert = quotes.joinToString(separator = ",") { it.name }

        return clientFactory.makeClient()
            .getLatestListings(convert)
            .map { response -> RequestState.wrap(response) }
            .onErrorReturn { RequestState.connectionFailed() }
    }

    override fun getCurrenciesInfo(
        ids: List<Long>
    ): Observable<RequestState<Map<String, CurrencyInfoResponse>>> {
        val request = ids.joinToString(separator = ",")

        return clientFactory.makeClient()
            .getCurrencyInfo(request)
            .map { response -> RequestState.wrap(response) }
            .onErrorReturn { RequestState.connectionFailed() }
    }
}