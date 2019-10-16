package by.minsk.polina_pasevina.cryptocurrency.injection.common

import by.minsk.polina_pasevina.cryptocurrency.network.CoinMarketApi
import by.minsk.polina_pasevina.cryptocurrency.network.CoinMarketApiImpl
import by.minsk.polina_pasevina.cryptocurrency.network.CoinMarketClientFactory
import by.minsk.polina_pasevina.cryptocurrency.network.CoinMarketClientFactoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NetworkModule {
    @Provides
    @Singleton
    fun provideRetrofitClientFactory(): CoinMarketClientFactory {
        return CoinMarketClientFactoryImpl()
    }

    @Provides
    @Singleton
    fun provideCoinMarketApi(clientFactory: CoinMarketClientFactory): CoinMarketApi {
        return CoinMarketApiImpl(clientFactory)
    }
}