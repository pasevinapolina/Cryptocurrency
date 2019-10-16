package by.minsk.polina_pasevina.cryptocurrency.interactors

import io.reactivex.Observable
import java.math.BigDecimal

interface GetCryptoCurrenciesInteractor {
    fun get(): Observable<GetCryptocurrenciesContract>
}

sealed class GetCryptocurrenciesContract {
    data class Success(val currencies: List<CryptocurrencyContract>) : GetCryptocurrenciesContract()

    data class Failed(val error: Throwable?) : GetCryptocurrenciesContract()
}

data class CryptocurrencyContract(
    val id: Long,
    val name: String,
    val usdPrice: BigDecimal?,
    val imageUrl: String?
)