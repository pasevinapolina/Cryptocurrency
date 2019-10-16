package by.minsk.polina_pasevina.cryptocurrency.presentation.currency_list

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import by.minsk.polina_pasevina.cryptocurrency.R
import by.minsk.polina_pasevina.cryptocurrency.injection.Injector
import by.minsk.polina_pasevina.cryptocurrency.injection.currency_list.CurrencyListModule
import by.minsk.polina_pasevina.cryptocurrency.presentation.common.MvpActivity
import by.minsk.polina_pasevina.cryptocurrency.presentation.common.updateVisibility
import kotlinx.android.synthetic.main.activity_currency_list.*
import java.io.Serializable

class CurrencyListActivity : MvpActivity<CurrencyListView, CurrencyListPresenter>() {

    private val adapter get() = recyclerViewCurrencies.adapter as CurrencyAdapter

    override val contentViewId = R.layout.activity_currency_list

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()
    }

    private fun initViews() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)

        recyclerViewCurrencies.layoutManager = LinearLayoutManager(this)
        recyclerViewCurrencies.adapter = CurrencyAdapter { id -> presenter?.onCurrencyClicked(id) }
        recyclerViewCurrencies.setHasFixedSize(false)
    }

    override fun createPresenter(retainedState: Serializable?): CurrencyListPresenter {
        return Injector.appComponent.plus(CurrencyListModule()).getPresenter()
    }

    override fun getView(): CurrencyListView {
        return object : CurrencyListView {

            override fun render(state: CurrencyListViewState) {
                renderLoading(state.loading)
                renderError(state.showError)
                adapter.update(state.currencies)
            }

            override fun openCurrencyDetailsScreen(id: String) {
                // TODO: startActivity
            }
        }
    }

    private fun renderLoading(show: Boolean) {
        progressBar.updateVisibility(show)
    }

    private fun renderError(show: Boolean) {
        textViewError.updateVisibility(show)
    }
}