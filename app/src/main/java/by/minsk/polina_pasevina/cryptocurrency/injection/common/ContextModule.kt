package by.minsk.polina_pasevina.cryptocurrency.injection.common

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class ContextModule(private val context: Context) {

    @Provides
    fun provideContext(): Context = context
}