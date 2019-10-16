package by.minsk.polina_pasevina.cryptocurrency.gateways.providers

import okhttp3.OkHttpClient

class ClientProviderImpl : ClientProvider {

    override fun getAuthorizedClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor())
            .build()
    }
}