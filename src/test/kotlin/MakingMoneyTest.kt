import java.math.BigDecimal
import java.math.RoundingMode
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MakingMoneyTest {
    private companion object {
        val orders = mutableListOf(
            Order(code = "O", amount = BigDecimal.TEN),
            Order(code = "H", amount = BigDecimal.TEN),
            Order(code = "C", amount = BigDecimal.TEN),
            Order(code = "Th", amount = BigDecimal.TEN),
            Order(code = "Hh", amount = BigDecimal.TEN),
            Order(code = "T", amount = BigDecimal.TEN),
        )
    }

    @Test
    internal fun `should calculate total amount of a list of given orders`() {
        val expectedAmount = BigDecimal(3).setScale(2, RoundingMode.CEILING)

        val drinkMaker = DrinkMaker(orders)
        val totalAmount = drinkMaker.getTotalAmount()

        assertThat(totalAmount).isEqualTo(expectedAmount)
    }

    @Test
    internal fun `should print order history, containing how many of each drink was sold and the total amount`() {
        val drinkMaker = DrinkMaker(orders)

        drinkMaker.showOrderHistory()
    }
}