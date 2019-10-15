package by.minsk.polina_pasevina.cryptocurrency.presentation.currency_list

data class CurrencyListViewState(
    val loading: Boolean,
    val currencies: List<CurrencyViewState>
) {
    companion object {
        val INITIAL = CurrencyListViewState(
            loading = true,
            currencies = listOf()
        )
    }
}

data class CurrencyViewState(
    val id: Int,
    val name: String,
    val price: Float,
    val imageUrl: String
)