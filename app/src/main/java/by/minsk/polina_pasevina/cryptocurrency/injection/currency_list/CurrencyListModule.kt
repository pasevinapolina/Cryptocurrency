package by.minsk.polina_pasevina.cryptocurrency.injection.currency_list

import by.minsk.polina_pasevina.cryptocurrency.interactors.GetCryptoCurrenciesInteractor
import by.minsk.polina_pasevina.cryptocurrency.presentation.currency_list.CurrencyListPresenter
import by.minsk.polina_pasevina.cryptocurrency.presentation.currency_list.CurrencyListPresenterImpl
import dagger.Module
import dagger.Provides

@Module
class CurrencyListModule {

    @Provides
    fun provideCurrencyListPresenter(
        getCryptoCurrenciesInteractor: GetCryptoCurrenciesInteractor
    ): CurrencyListPresenter {
        return CurrencyListPresenterImpl(getCryptoCurrenciesInteractor)
    }
}