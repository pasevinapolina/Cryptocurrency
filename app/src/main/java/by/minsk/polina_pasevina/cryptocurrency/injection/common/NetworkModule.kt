package by.minsk.polina_pasevina.cryptocurrency.injection.common

import by.minsk.polina_pasevina.cryptocurrency.network.CoinMarketClientFactory
import by.minsk.polina_pasevina.cryptocurrency.network.CoinMarketClientFactoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ContextModule::class])
class NetworkModule(private val contextModule: ContextModule) {
    @Provides
    @Singleton
    fun provideRetrofitClientFactory(): CoinMarketClientFactory {
        return CoinMarketClientFactoryImpl()
    }
}