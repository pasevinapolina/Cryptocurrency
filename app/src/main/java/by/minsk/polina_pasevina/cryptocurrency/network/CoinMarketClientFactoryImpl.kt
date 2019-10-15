package by.minsk.polina_pasevina.cryptocurrency.network

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.jackson.JacksonConverterFactory

class CoinMarketClientFactoryImpl : CoinMarketClientFactory {

    private val rxJava2CallAdapterFactory = RxJava2CallAdapterFactory.create()

    private val jacksonConverterFactory = JacksonConverterFactory.create()

    override fun makeClient(): CoinMarketClient {
        val retrofit = Retrofit.Builder()
            .addCallAdapterFactory(rxJava2CallAdapterFactory)
            .addConverterFactory(jacksonConverterFactory)
            .baseUrl(EndPoints.BASE_URL)
            .build()

        return retrofit.create(CoinMarketClient::class.java)
    }
}