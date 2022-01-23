import java.math.BigDecimal

data class Order(
    val code: String,
    val sugar: Int? = null,
    val stick: Int? = null,
    val amount: BigDecimal = BigDecimal.ZERO,
    val message: String? = null
) {
    fun isAMessage() = code == "M"
}
