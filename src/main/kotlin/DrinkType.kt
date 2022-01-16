import java.math.BigDecimal
import java.math.RoundingMode

enum class DrinkType(val code: String, val price: BigDecimal) {
    TEA("T", BigDecimal(0.4)),
    CHOCOLATE("H", BigDecimal(0.5)),
    COFFEE("C", BigDecimal(0.6)),
    ORANGE_JUICE("O", BigDecimal(0.6)),
    UNKNOWN("", BigDecimal(-1));

    fun getClassicName() = this.name.replace("_", " ").lowercase()

    fun getRoundedPrice(): BigDecimal = price.setScale(2, RoundingMode.HALF_UP)

    companion object {
        fun getRoundedPriceByCode(code: String): BigDecimal = values().find { it.code == code.replace("h", "") }?.getRoundedPrice() ?: BigDecimal.ZERO
    }
}