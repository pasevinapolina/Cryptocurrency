package by.minsk.polina_pasevina.cryptocurrency.presentation.currency_list

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
    val id: String,
    val name: String,
    val price: Float?,
    val imageUrl: String?
)