package by.minsk.polina_pasevina.cryptocurrency.presentation.common

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import java.io.Serializable

abstract class MvpActivity<V : MvpView<*>, P : MvpPresenter<V>> : AppCompatActivity() {

    var presenter: P? = null
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(contentViewId)
        presenter = (lastCustomNonConfigurationInstance as? P) ?: createPresenter()
    }

    override fun onResume() {
        super.onResume()
        val view = getView()
        presenter?.attachView(view)
    }

    override fun onRetainCustomNonConfigurationInstance(): Any {
        return presenter as Any
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        presenter?.detachView()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.onDestroy()
        presenter = null
    }

    abstract val contentViewId: Int

    abstract fun getView(): V

    abstract fun createPresenter(retainedState: Serializable? = null): P
}