package by.minsk.polina_pasevina.cryptocurrency.network

import com.fasterxml.jackson.annotation.JsonProperty

data class CryptocurrencyResponse(
    @JsonProperty("id")
    val id: String,

    @JsonProperty("name")
    val name: String,

    @JsonProperty("symbol")
    val symbol: String,

    @JsonProperty("slug")
    val slug: String,

    @JsonProperty("is_active")
    val isActive: Boolean,

    @JsonProperty("first_historical_data")
    val firstHistoricalData: String,

    @JsonProperty("last_historical_data")
    val lastHistoricalData: String,

    @JsonProperty("id")
    val platform: String
)