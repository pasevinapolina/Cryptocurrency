package by.minsk.polina_pasevina.cryptocurrency.injection.common

import by.minsk.polina_pasevina.cryptocurrency.network.ClientProvider
import by.minsk.polina_pasevina.cryptocurrency.network.ClientProviderImpl
import dagger.Module
import dagger.Provides

@Module
class ProvidersModule {

    @Provides
    fun provideClientProvider(): ClientProvider {
        return ClientProviderImpl()
    }
}
