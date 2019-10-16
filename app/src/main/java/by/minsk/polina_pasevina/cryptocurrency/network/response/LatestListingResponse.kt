package by.minsk.polina_pasevina.cryptocurrency.network.response

import by.minsk.polina_pasevina.cryptocurrency.network.MoneySerializer
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import java.math.BigDecimal

data class LatestListingResponse(
    @JsonProperty("id")
    val id: Long,

    @JsonProperty("name")
    val name: String,

    @JsonProperty("symbol")
    val symbol: String,

    @JsonProperty("slug")
    val slug: String,

    @JsonProperty("cmc_rank")
    val rank: Int,

    @JsonProperty("quote")
    val quotes: Map<String, QuoteResponse>
)

data class QuoteResponse(
    @JsonProperty("price")
    @JsonSerialize(using = MoneySerializer::class)
    val price: BigDecimal,

    @JsonProperty("last_updated")
    val lastUpdated: String
)