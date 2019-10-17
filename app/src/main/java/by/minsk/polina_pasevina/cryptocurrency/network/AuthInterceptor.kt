package by.minsk.polina_pasevina.cryptocurrency.network

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader(
                HEADER_KEY_API_KEY,
                API_KEY
            )
            .addHeader(
                HEADER_KEY_CONTENT_TYPE,
                APPLICATION_JSON
            )
            .build()
        return chain.proceed(request)
    }

    companion object {
        private const val HEADER_KEY_API_KEY = "X-CMC_PRO_API_KEY"
        private const val HEADER_KEY_CONTENT_TYPE = "Accept"

        private const val API_KEY = "8a820b3f-da8a-4f99-9013-d2f70315d413"
        private const val APPLICATION_JSON = "application/json"
    }
}