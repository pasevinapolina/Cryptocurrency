package by.minsk.polina_pasevina.cryptocurrency.network

interface CoinMarketClientFactory {
    fun makeClient(): CoinMarketClient
}