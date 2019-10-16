package by.minsk.polina_pasevina.cryptocurrency.network

import by.minsk.polina_pasevina.cryptocurrency.entities.QuoteType
import by.minsk.polina_pasevina.cryptocurrency.network.request.RequestState
import by.minsk.polina_pasevina.cryptocurrency.network.request.RequestStatus
import by.minsk.polina_pasevina.cryptocurrency.network.response.LatestListingResponse
import io.reactivex.Observable

class CoinMarketApiImpl(private val clientFactory: CoinMarketClientFactory) : CoinMarketApi {

    override fun getCurrencyList(
        quotes: List<QuoteType>
    ): Observable<RequestState<List<LatestListingResponse>>> {
        val convert = quotes.joinToString(separator = ",") { it.name }

        return clientFactory.makeClient()
            .getLatestListings(convert)
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