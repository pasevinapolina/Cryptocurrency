package by.minsk.polina_pasevina.cryptocurrency.network.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class BaseResponse<T>(
    @JsonProperty("data")
    val data: T?,

    @JsonProperty("status")
    val status: StatusResponse
)

data class StatusResponse(
    @JsonProperty("timestamp")
    val timestamp: String,

    @JsonProperty("error_code")
    val errorCode: Int?,

    @JsonProperty("error_message")
    val errorMessage: String?,

    @JsonProperty("elapsed")
    val elapsed: Int,

    @JsonProperty("credit_count")
    val creditCount: Int
)