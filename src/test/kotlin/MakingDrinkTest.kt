import java.math.BigDecimal
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MakingDrinkTest {

    @Test
    internal fun `drink maker should make a tea with 1 sugar and a stick`() {
        val initialOrder = Order(code = "T", sugar = 1, stick = 0, amount = BigDecimal(0.5))
        val expectedDrink = "Drink maker will make one tea with one sugar and a stick"

        val drinkMaker = DrinkMaker()
        val drink = drinkMaker.makeDrinkOrForwardMessage(initialOrder)

        assertThat(drink).isEqualTo(expectedDrink)
    }

    @Test
    internal fun `drink maker should make a chocolate with no sugar and no stick`() {
        val initialOrder = Order(code = "H", amount = BigDecimal(5))
        val expectedDrink = "Drink maker will make one chocolate with no sugar and no stick"

        val drinkMaker = DrinkMaker()
        val drink = drinkMaker.makeDrinkOrForwardMessage(initialOrder)

        assertThat(drink).isEqualTo(expectedDrink)
    }

    @Test
    internal fun `drink maker should make a coffee with 2 sugars and a stick`() {
        val initialOrder = Order(code = "C", sugar = 2, stick = 0, amount = BigDecimal(5))
        val expectedDrink = "Drink maker will make one coffee with two sugars and a stick"

        val drinkMaker = DrinkMaker()
        val drink = drinkMaker.makeDrinkOrForwardMessage(initialOrder)

        assertThat(drink).isEqualTo(expectedDrink)
    }

    @Test
    internal fun `drink maker should forward any message received`() {
        val initialOrder = Order(code = "M", message = "you are a super cool drink maker")
        val expectedMessage = "Drink maker receives a message: you are a super cool drink maker"

        val drinkMaker = DrinkMaker()
        val message = drinkMaker.makeDrinkOrForwardMessage(initialOrder)

        assertThat(message).isEqualTo(expectedMessage)
    }
}