package by.minsk.polina_pasevina.cryptocurrency.network

data class RequestState<T>(
    private val status: RequestStatus,
    val error: ClientException?,
    val data: T?
) {

    companion object {
        fun <T> wrap(response: BaseResponse<T>) : RequestState<T> {
            return RequestState(
                status = if (response.status.errorCode == null) RequestStatus.SUCCESSFUL else RequestStatus.FAILED,
                error = response.status.toClientException(),
                data = response.data
            )
        }

        private fun StatusResponse.toClientException() = when (errorCode) {
            null -> null
            else -> ConnectionFailedException(errorMessage)
        }
    }
}

enum class RequestStatus {
    SUCCESSFUL,
    FAILED
}