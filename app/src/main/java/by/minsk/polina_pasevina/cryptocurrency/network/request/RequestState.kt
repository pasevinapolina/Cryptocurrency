package by.minsk.polina_pasevina.cryptocurrency.network.request

import by.minsk.polina_pasevina.cryptocurrency.network.BadRequestException
import by.minsk.polina_pasevina.cryptocurrency.network.ClientException
import by.minsk.polina_pasevina.cryptocurrency.network.ConnectionFailedException
import by.minsk.polina_pasevina.cryptocurrency.network.response.BaseResponse
import by.minsk.polina_pasevina.cryptocurrency.network.response.StatusResponse

data class RequestState<T>(
    val status: RequestStatus,
    val error: ClientException?,
    val data: T?
) {

    companion object {
        fun <T> wrap(response: BaseResponse<T>) : RequestState<T> {
            return RequestState(
                status = if (response.status.errorCode == 0) RequestStatus.SUCCESSFUL else RequestStatus.FAILED,
                error = response.status.toClientException(),
                data = response.data
            )
        }

        private fun StatusResponse.toClientException() = when (errorCode) {
            0 -> null
            404 -> BadRequestException(errorMessage)
            else -> ConnectionFailedException(
                errorMessage
            )
        }
    }
}

enum class RequestStatus {
    SUCCESSFUL,
    FAILED
}