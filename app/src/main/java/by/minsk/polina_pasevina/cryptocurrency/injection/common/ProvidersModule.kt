package by.minsk.polina_pasevina.cryptocurrency.injection.common

import by.minsk.polina_pasevina.cryptocurrency.gateways.providers.ClientProvider
import by.minsk.polina_pasevina.cryptocurrency.gateways.providers.ClientProviderImpl
import dagger.Module
import dagger.Provides

@Module
class ProvidersModule {

    @Provides
    fun provideClientProvider(): ClientProvider {
        return ClientProviderImpl()
    }
}
