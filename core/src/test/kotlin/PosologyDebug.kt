
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.DeserializationFeature
import org.junit.jupiter.api.Test
import java.time.Instant
import java.time.OffsetDateTime
import java.time.ZoneOffset

class PosologyDebug {
    @Test
    internal fun print_posology_all_since_mid_february() {
        val filename = "posology_all_since_mid_february.json"
        val values = parse(filename)

        values.sortedBy { it._id.`$oid` }.forEach { println(it) }

    }
    @Test
    internal fun print_posology_all_null() {
        val filename = "posology_all_null.json"
        val values = parse(filename)

        values.sortedBy { it._id.`$oid` }.forEach { println(it) }
    }

    private fun parse(filename: String): List<SampleData> {
        val data: String = Thread.currentThread().contextClassLoader.getResourceAsStream(filename).use {
            String(it.readBytes())
        }

        val listOfSampleDataTypeRef: TypeReference<List<SampleData>> = object : TypeReference<List<SampleData>>() {}

        val values = KObjectMapper.newInstance().apply {
            configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        }.readValue<List<SampleData>>(data, listOfSampleDataTypeRef)
        return values
    }
}

data class SampleData(
        val drugSubstancesHash: String?,
        val drugId: NumberLongHolder,
        val _id: OidHolder,
        val validationDate: OffsetDateTime?,
        val validatedBy: String?
)
data class OidHolder(val `$oid`: String) {
    fun timestamp(): OffsetDateTime {
        val maybeTs = `$oid`.substring(0, 8).toLong(16)
        return Instant.ofEpochSecond(maybeTs).atOffset(ZoneOffset.UTC)
    }

    override fun toString(): String {
        return "OidHolder(`\$oid`='$`$oid`', timestamp=${timestamp()})"
    }
}
data class NumberLongHolder(val `$numberLong`: String) {

}