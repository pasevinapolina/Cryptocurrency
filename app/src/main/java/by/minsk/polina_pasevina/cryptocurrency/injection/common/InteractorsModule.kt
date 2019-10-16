package by.minsk.polina_pasevina.cryptocurrency.injection.common

import by.minsk.polina_pasevina.cryptocurrency.interactors.GetCryptoCurrenciesInteractor
import by.minsk.polina_pasevina.cryptocurrency.interactors.GetCryptoCurrenciesInteractorImpl
import by.minsk.polina_pasevina.cryptocurrency.network.CoinMarketApi
import dagger.Module
import dagger.Provides

@Module(includes = [NetworkModule::class])
class InteractorsModule {

    @Provides
    fun provideGetCryptocurrenciesInteractor(
        coinMarketApi: CoinMarketApi
    ): GetCryptoCurrenciesInteractor {
        return GetCryptoCurrenciesInteractorImpl(coinMarketApi)
    }
}