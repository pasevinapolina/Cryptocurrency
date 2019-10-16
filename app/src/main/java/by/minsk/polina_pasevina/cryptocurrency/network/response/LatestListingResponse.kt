package by.minsk.polina_pasevina.cryptocurrency.network.response

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import java.math.BigDecimal


data class LatestListingResponse(
    @JsonProperty("id")
    val id: Int,

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

class MoneySerializer : JsonSerializer<BigDecimal>() {
    override fun serialize(value: BigDecimal, generator: JsonGenerator, provider: SerializerProvider) {
        generator.writeString(value.setScale(2, BigDecimal.ROUND_HALF_UP).toString())
    }
}