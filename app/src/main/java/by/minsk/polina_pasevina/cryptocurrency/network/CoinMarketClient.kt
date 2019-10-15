package by.minsk.polina_pasevina.cryptocurrency.network

import io.reactivex.Observable
import retrofit2.http.GET

interface CoinMarketClient {
    @GET(EndPoints.GET_CURRENCY_LIST)
    fun getCurrencyList(): Observable<BaseResponse<CryptocurrencyResponse>>
}