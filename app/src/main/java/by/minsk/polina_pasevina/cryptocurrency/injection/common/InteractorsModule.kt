package by.minsk.polina_pasevina.cryptocurrency.injection.common

import by.minsk.polina_pasevina.cryptocurrency.interactors.GetCryptocurrenciesInteractor
import by.minsk.polina_pasevina.cryptocurrency.interactors.GetCryptocurrenciesInteractorImpl
import by.minsk.polina_pasevina.cryptocurrency.network.CoinMarketApi
import dagger.Module
import dagger.Provides

@Module(includes = [NetworkModule::class])
class InteractorsModule {

    @Provides
    fun provideGetCryptocurrenciesInteractor(
        coinMarketApi: CoinMarketApi
    ): GetCryptocurrenciesInteractor {
        return GetCryptocurrenciesInteractorImpl(coinMarketApi)
    }
}