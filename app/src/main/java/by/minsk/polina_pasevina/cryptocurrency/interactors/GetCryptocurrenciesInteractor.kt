package by.minsk.polina_pasevina.cryptocurrency.interactors

import io.reactivex.Observable

interface GetCryptocurrenciesInteractor {
    fun get(): Observable<GetCryptocurrenciesContract>
}

sealed class GetCryptocurrenciesContract {
    data class Success(val currencies: List<CryptocurrencyContract>) : GetCryptocurrenciesContract()

    data class Failed(val error: Throwable?) : GetCryptocurrenciesContract()
}

data class CryptocurrencyContract(
    val id: Int,
    val name: String,
    val usdPrice: Float,
    val imageUrl: String
)