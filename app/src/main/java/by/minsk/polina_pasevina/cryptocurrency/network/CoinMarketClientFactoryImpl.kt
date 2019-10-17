package by.minsk.polina_pasevina.cryptocurrency.network

import by.minsk.polina_pasevina.cryptocurrency.gateways.network.EndPoints
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.jackson.JacksonConverterFactory

class CoinMarketClientFactoryImpl(
    private val clientProvider: ClientProvider
) : CoinMarketClientFactory {

    private val objectMapper = ObjectMapper()
        .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)

    private val rxJava2CallAdapterFactory = RxJava2CallAdapterFactory.create()

    private val jacksonConverterFactory = JacksonConverterFactory.create(objectMapper)

    override fun makeClient(): CoinMarketClient {
        val retrofit = Retrofit.Builder()
            .client(clientProvider.getAuthorizedClient())
            .addCallAdapterFactory(rxJava2CallAdapterFactory)
            .addConverterFactory(jacksonConverterFactory)
            .baseUrl(EndPoints.BASE_URL)
            .build()

        return retrofit.create(CoinMarketClient::class.java)
    }
}