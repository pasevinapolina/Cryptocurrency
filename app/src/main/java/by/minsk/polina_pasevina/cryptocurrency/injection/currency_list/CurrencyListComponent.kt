package by.minsk.polina_pasevina.cryptocurrency.injection.currency_list

import by.minsk.polina_pasevina.cryptocurrency.presentation.currency_list.CurrencyListPresenter
import dagger.Subcomponent

@Subcomponent(modules = [CurrencyListModule::class])
interface CurrencyListComponent {

    fun getPresenter(): CurrencyListPresenter
}