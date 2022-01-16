import java.math.BigDecimal
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GoingIntoBusinessTest {

    @Test
    internal fun `drink maker should not make a coffee with 3 sugar and a stick if the correct amount is not given`() {
        val initialOrder = Order(code = "C", sugar = 3, stick = 0, amount = BigDecimal(0.5))
        val expectedDrink = "Drink maker receives a message: Not enough money for coffee (missing 0.10 €)"

        val drinkMaker = DrinkMaker()
        val drink = drinkMaker.makeDrinkOrForwardMessage(initialOrder)

        assertThat(drink).isEqualTo(expectedDrink)
    }

    @Test
    internal fun `drink maker should make a chocolate with no sugar and no stick if the correct amount is given`() {
        val initialOrder = Order(code = "H", amount = BigDecimal(2))
        val expectedDrink = "Drink maker will make one chocolate with no sugar and no stick"

        val drinkMaker = DrinkMaker()
        val drink = drinkMaker.makeDrinkOrForwardMessage(initialOrder)

        assertThat(drink).isEqualTo(expectedDrink)
    }
}