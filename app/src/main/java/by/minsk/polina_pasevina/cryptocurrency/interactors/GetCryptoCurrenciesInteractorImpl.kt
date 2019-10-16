package by.minsk.polina_pasevina.cryptocurrency.interactors

import by.minsk.polina_pasevina.cryptocurrency.entities.QuoteType
import by.minsk.polina_pasevina.cryptocurrency.network.CoinMarketApi
import by.minsk.polina_pasevina.cryptocurrency.network.response.CryptoCurrencyResponse
import by.minsk.polina_pasevina.cryptocurrency.network.request.RequestStatus
import io.reactivex.Observable

class GetCryptoCurrenciesInteractorImpl(
    private val coinMarketApi: CoinMarketApi
) : GetCryptoCurrenciesInteractor {

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
        cryptoCurrencies: List<CryptoCurrencyResponse>
    ): Observable<GetCryptocurrenciesContract> {
        val ids = cryptoCurrencies.map { it.id }

        return coinMarketApi.getCurrenciesInfo(ids)
            .map { s ->
                when (s.status) {
                    RequestStatus.SUCCESSFUL -> {
                        val imageUrls = s.data.orEmpty().values.associate { it.id to it.logo }
                        val currencies = cryptoCurrencies
                            .sortedBy { it.rank }
                            .map { currency -> currency.toContract(imageUrls[currency.id]) }
                        GetCryptocurrenciesContract.Success(currencies)
                    }
                    RequestStatus.FAILED -> GetCryptocurrenciesContract.Failed(s.error)
                }
            }
    }

    private fun CryptoCurrencyResponse.toContract(imageUrl: String?) =
        CryptocurrencyContract(
            id = id,
            name = name,
            usdPrice = quotes[QuoteType.USD.name]?.price,
            imageUrl = imageUrl
        )
}