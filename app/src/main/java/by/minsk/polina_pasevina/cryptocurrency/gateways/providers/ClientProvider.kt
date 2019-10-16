package by.minsk.polina_pasevina.cryptocurrency.gateways.providers

import okhttp3.OkHttpClient

interface ClientProvider {
    fun getAuthorizedClient(): OkHttpClient
}