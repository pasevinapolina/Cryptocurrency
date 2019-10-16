package by.minsk.polina_pasevina.cryptocurrency.interactors

import by.minsk.polina_pasevina.cryptocurrency.entities.QuoteType
import by.minsk.polina_pasevina.cryptocurrency.network.CoinMarketApi
import by.minsk.polina_pasevina.cryptocurrency.network.response.LatestListingResponse
import by.minsk.polina_pasevina.cryptocurrency.network.request.RequestStatus
import io.reactivex.Observable

class GetCryptocurrenciesInteractorImpl(
    private val coinMarketApi: CoinMarketApi
) : GetCryptocurrenciesInteractor {

    override fun get(): Observable<GetCryptocurrenciesContract> {
        return coinMarketApi.getCurrencyList(listOf(QuoteType.USD))
            .map { s ->
                when (s.status) {
                    RequestStatus.SUCCESSFUL -> {
                        val currencies = s.data.orEmpty()
                            .sortedBy { it.rank }
                            .map(responseToContract)
                        GetCryptocurrenciesContract.Success(currencies)
                    }
                    RequestStatus.FAILED -> {
                        GetCryptocurrenciesContract.Failed(s.error)
                    }
                }
            }
    }

    private val responseToContract = { response: LatestListingResponse ->
        CryptocurrencyContract(
            id = response.id.toString(),
            name = response.name,
            usdPrice = response.quotes[QuoteType.USD.name]?.price,
            imageUrl = ""
        )
    }
}