package by.minsk.polina_pasevina.cryptocurrency.injection.common

import by.minsk.polina_pasevina.cryptocurrency.gateways.network.CoinMarketApi
import by.minsk.polina_pasevina.cryptocurrency.network.CoinMarketApiImpl
import by.minsk.polina_pasevina.cryptocurrency.network.CoinMarketClientFactory
import by.minsk.polina_pasevina.cryptocurrency.network.CoinMarketClientFactoryImpl
import by.minsk.polina_pasevina.cryptocurrency.network.ClientProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ProvidersModule::class])
class NetworkModule {
    @Provides
    @Singleton
    fun provideRetrofitClientFactory(clientProvider: ClientProvider): CoinMarketClientFactory {
        return CoinMarketClientFactoryImpl(clientProvider)
    }

    @Provides
    @Singleton
    fun provideCoinMarketApi(clientFactory: CoinMarketClientFactory): CoinMarketApi {
        return CoinMarketApiImpl(clientFactory)
    }
}