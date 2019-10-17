package by.minsk.polina_pasevina.cryptocurrency.network

import okhttp3.OkHttpClient

class ClientProviderImpl : ClientProvider {

    override fun getAuthorizedClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor())
            .build()
    }
}