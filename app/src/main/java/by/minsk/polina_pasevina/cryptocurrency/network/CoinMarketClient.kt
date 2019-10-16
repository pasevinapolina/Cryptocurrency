package by.minsk.polina_pasevina.cryptocurrency.network

import by.minsk.polina_pasevina.cryptocurrency.network.response.BaseResponse
import by.minsk.polina_pasevina.cryptocurrency.network.response.CurrencyInfoResponse
import by.minsk.polina_pasevina.cryptocurrency.network.response.LatestListingResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface CoinMarketClient {
    @GET(EndPoints.GET_LATEST_LISTINGS)
    fun getLatestListings(
        @Query("convert") convert: String
    ): Observable<BaseResponse<List<LatestListingResponse>>>

    @GET(EndPoints.GET_CURRENCY_INFO)
    fun getCurrencyInfo(
        @Query("id") currencyIds: String
    ): Observable<BaseResponse<Map<String, CurrencyInfoResponse>>>
}