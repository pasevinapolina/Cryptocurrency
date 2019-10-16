package by.minsk.polina_pasevina.cryptocurrency.interactors

import by.minsk.polina_pasevina.cryptocurrency.entities.QuoteType
import by.minsk.polina_pasevina.cryptocurrency.network.CoinMarketApi
import by.minsk.polina_pasevina.cryptocurrency.network.response.LatestListingResponse
import by.minsk.polina_pasevina.cryptocurrency.network.request.RequestStatus
import by.minsk.polina_pasevina.cryptocurrency.network.response.CurrencyInfoResponse
import io.reactivex.Observable

class GetCryptocurrenciesInteractorImpl(
    private val coinMarketApi: CoinMarketApi
) : GetCryptocurrenciesInteractor {

    override fun get(): Observable<GetCryptocurrenciesContract> {
        return coinMarketApi.getCurrencyList(listOf(QuoteType.USD))
            .flatMap { s ->
                when (s.status) {
                    RequestStatus.SUCCESSFUL -> getCurrenciesInfo(s.data.orEmpty())
                    RequestStatus.FAILED ->
                        Observable.just(GetCryptocurrenciesContract.Failed(s.error))
                }
            }
    }

    private fun getCurrenciesInfo(
        currencies: List<LatestListingResponse>
    ): Observable<GetCryptocurrenciesContract> {
        val ids = currencies.map { it.id }

        return coinMarketApi.getCurrenciesInfo(ids)
            .map { s ->
                when (s.status) {
                    RequestStatus.SUCCESSFUL -> {
                        val imageUrls = s.data.orEmpty().values.associate { it.id to it.logo }
                        val currencyList = currencies
                            .sortedBy { it.rank }
                            .map { responseToContract(it, imageUrls[it.id]) }
                        GetCryptocurrenciesContract.Success(currencyList)
                    }
                    RequestStatus.FAILED -> GetCryptocurrenciesContract.Failed(s.error)
                }
            }
    }

    private val responseToContract = { response: LatestListingResponse,
                                       imageUrl: String? ->
        CryptocurrencyContract(
            id = response.id.toString(),
            name = response.name,
            usdPrice = response.quotes[QuoteType.USD.name]?.price,
            imageUrl = imageUrl
        )
    }
}