package by.minsk.polina_pasevina.cryptocurrency.network.response

import com.fasterxml.jackson.annotation.JsonProperty

data class CurrencyInfoResponse(
    @JsonProperty("id")
    val id: Long,

    @JsonProperty("logo")
    val logo: String,

    @JsonProperty("description")
    val description: String,

    @JsonProperty("category")
    val category: Long,

    @JsonProperty("date_added")
    val addedDate: String

)