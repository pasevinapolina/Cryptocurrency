package by.minsk.polina_pasevina.cryptocurrency.entities

import org.joda.time.DateTime

data class Quote(
    val type: QuoteType,
    val price: Float,
    val lastUpdated: DateTime
)

enum class QuoteType {
    USD,
    BTC;
}