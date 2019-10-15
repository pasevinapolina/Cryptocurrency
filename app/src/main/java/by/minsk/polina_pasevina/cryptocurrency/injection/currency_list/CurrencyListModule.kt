package by.minsk.polina_pasevina.cryptocurrency.injection.currency_list

import by.minsk.polina_pasevina.cryptocurrency.interactors.GetCryptocurrenciesInteractor
import by.minsk.polina_pasevina.cryptocurrency.presentation.currency_list.CurrencyListPresenter
import by.minsk.polina_pasevina.cryptocurrency.presentation.currency_list.CurrencyListPresenterImpl
import dagger.Module
import dagger.Provides

@Module
class CurrencyListModule {

    @Provides
    fun provideCurrencyListPresenter(
        getCryptocurrenciesInteractor: GetCryptocurrenciesInteractor
    ): CurrencyListPresenter {
        return CurrencyListPresenterImpl(
            getCryptocurrenciesInteractor
        )
    }
}