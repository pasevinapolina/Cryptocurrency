package by.minsk.polina_pasevina.cryptocurrency.presentation.currency_list

import java.math.BigDecimal

data class CurrencyListViewState(
    val loading: Boolean,
    val currencies: List<CurrencyViewState>,
    val showError: Boolean
) {
    companion object {
        val INITIAL = CurrencyListViewState(
            loading = true,
            currencies = listOf(),
            showError = false
        )
    }
}

data class CurrencyViewState(
    val id: Long,
    val name: String,
    val price: BigDecimal?,
    val imageUrl: String?
)