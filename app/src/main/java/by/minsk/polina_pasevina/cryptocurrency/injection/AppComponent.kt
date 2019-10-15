package by.minsk.polina_pasevina.cryptocurrency.injection

import by.minsk.polina_pasevina.cryptocurrency.injection.common.ContextModule
import by.minsk.polina_pasevina.cryptocurrency.injection.common.InteractorsModule
import by.minsk.polina_pasevina.cryptocurrency.injection.currency_list.CurrencyListComponent
import by.minsk.polina_pasevina.cryptocurrency.injection.currency_list.CurrencyListModule
import by.minsk.polina_pasevina.cryptocurrency.os.App
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ContextModule::class, InteractorsModule::class])
interface AppComponent {

    fun inject(application: App)

    fun plus(module: CurrencyListModule): CurrencyListComponent
}