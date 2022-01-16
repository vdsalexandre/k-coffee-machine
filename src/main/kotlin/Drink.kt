import java.math.BigDecimal
import java.math.RoundingMode

enum class Drink(val code: String, val price: BigDecimal) {
    TEA("T", BigDecimal(0.4)),
    CHOCOLATE("H", BigDecimal(0.5)),
    COFFEE("C", BigDecimal(0.6)),
    ORANGE_JUICE("O", BigDecimal(0.6)),
    UNKNOWN("", BigDecimal(-1.0));

    fun getPrice(code: String): BigDecimal = valueOf(code).price.setScale(2, RoundingMode.HALF_UP)
}