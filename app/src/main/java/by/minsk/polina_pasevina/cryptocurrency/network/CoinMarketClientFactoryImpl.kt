package by.minsk.polina_pasevina.cryptocurrency.network

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.jackson.JacksonConverterFactory

class CoinMarketClientFactoryImpl : CoinMarketClientFactory {

    private val objectMapper = ObjectMapper()
        .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)

    private val rxJava2CallAdapterFactory = RxJava2CallAdapterFactory.create()

    private val jacksonConverterFactory = JacksonConverterFactory.create(objectMapper)

    private val authorizedClient = OkHttpClient.Builder()
        .addInterceptor(object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                val request = chain.request().newBuilder()
                    .addHeader(HEADER_KEY_API_KEY, API_KEY)
                    .addHeader(HEADER_KEY_CONTENT_TYPE, APPLICATION_JSON)
                    .build()
                return chain.proceed(request)
            }
        })
        .build()

    override fun makeClient(): CoinMarketClient {
        val retrofit = Retrofit.Builder()
            .client(authorizedClient)
            .addCallAdapterFactory(rxJava2CallAdapterFactory)
            .addConverterFactory(jacksonConverterFactory)
            .baseUrl(EndPoints.BASE_URL)
            .build()

        return retrofit.create(CoinMarketClient::class.java)
    }

    companion object {
        private const val HEADER_KEY_API_KEY = "X-CMC_PRO_API_KEY"
        private const val HEADER_KEY_CONTENT_TYPE = "Accept"

        private const val API_KEY = "8a820b3f-da8a-4f99-9013-d2f70315d413"
        private const val APPLICATION_JSON = "application/json"
    }
}