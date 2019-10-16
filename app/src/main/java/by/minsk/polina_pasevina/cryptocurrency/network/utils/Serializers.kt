package by.minsk.polina_pasevina.cryptocurrency.network.utils

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import java.math.BigDecimal

class MoneySerializer : JsonSerializer<BigDecimal>() {
    override fun serialize(value: BigDecimal, generator: JsonGenerator, provider: SerializerProvider) {
        generator.writeString(value.setScale(2, BigDecimal.ROUND_HALF_UP).toString())
    }
}