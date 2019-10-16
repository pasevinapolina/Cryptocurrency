package by.minsk.polina_pasevina.cryptocurrency.network

import by.minsk.polina_pasevina.cryptocurrency.entities.QuoteType
import by.minsk.polina_pasevina.cryptocurrency.network.request.RequestState
import by.minsk.polina_pasevina.cryptocurrency.network.response.CurrencyInfoResponse
import by.minsk.polina_pasevina.cryptocurrency.network.response.LatestListingResponse
import io.reactivex.Observable

interface CoinMarketApi {
    fun getCurrencyList(quotes: List<QuoteType>): Observable<RequestState<List<LatestListingResponse>>>

    fun getCurrenciesInfo(ids: List<Long>): Observable<RequestState<Map<String, CurrencyInfoResponse>>>
}