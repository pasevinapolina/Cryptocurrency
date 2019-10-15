package by.minsk.polina_pasevina.cryptocurrency.presentation.currency_list

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import by.minsk.polina_pasevina.cryptocurrency.R
import by.minsk.polina_pasevina.cryptocurrency.injection.Injector
import by.minsk.polina_pasevina.cryptocurrency.injection.currency_list.CurrencyListModule
import by.minsk.polina_pasevina.cryptocurrency.presentation.common.MvpActivity
import kotlinx.android.synthetic.main.activity_currency_list.*
import java.io.Serializable

class CurrencyListActivity : MvpActivity<CurrencyListView, CurrencyListPresenter>() {

    private val adapter get() = recyclerViewCurrencies.adapter as CurrencyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_currency_list)
        initViews()
    }

    private fun initViews() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)

        recyclerViewCurrencies.layoutManager = LinearLayoutManager(this)
        recyclerViewCurrencies.adapter =
            CurrencyAdapter { id ->
                presenter?.onCurrencyClicked(id)
            }
        recyclerViewCurrencies.setHasFixedSize(false)
    }

    override fun createPresenter(retainedState: Serializable?): CurrencyListPresenter {
        return Injector.appComponent.plus(CurrencyListModule()).getPresenter()
    }

    override fun getView(): CurrencyListView {
        return object : CurrencyListView {

            override fun render(state: CurrencyListViewState) {
                renderLoading(state.loading)
                adapter.update(state.currencies)
            }
        }
    }

    private fun renderLoading(show: Boolean) {
        val visibility = if (show) View.VISIBLE else View.GONE
        if (progressBar.visibility != visibility) {
            progressBar.visibility = visibility
        }
    }
}