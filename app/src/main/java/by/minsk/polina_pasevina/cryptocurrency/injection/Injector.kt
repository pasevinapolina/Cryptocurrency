package by.minsk.polina_pasevina.cryptocurrency.injection

object Injector {
    lateinit var appComponent: AppComponent
        private set

    fun init(appComponent: AppComponent) {
        this.appComponent = appComponent
    }
}