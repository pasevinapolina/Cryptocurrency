package by.minsk.polina_pasevina.cryptocurrency.network

import okhttp3.OkHttpClient

interface ClientProvider {
    fun getAuthorizedClient(): OkHttpClient
}