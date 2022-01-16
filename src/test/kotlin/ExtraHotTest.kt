import java.math.BigDecimal
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ExtraHotTest {
    @Test
    internal fun `drink maker should make an orange juice`() {
        val initialOrder = Order(code = "O", amount = BigDecimal(0.6))
        val expectedDrink = "Drink maker will make one orange juice"

        val drinkMaker = DrinkMaker()
        val drink = drinkMaker.makeDrinkOrForwardMessage(initialOrder)

        assertThat(drink).isEqualTo(expectedDrink)
    }

    @Test
    internal fun `drink maker should make an extra hot coffee with no sugar`() {
        val initialOrder = Order(code = "Ch", amount = BigDecimal(1))
        val expectedDrink = "Drink maker will make an extra hot coffee with no sugar and no stick"

        val drinkMaker = DrinkMaker()
        val drink = drinkMaker.makeDrinkOrForwardMessage(initialOrder)

        assertThat(drink).isEqualTo(expectedDrink)
    }


    @Test
    internal fun `drink maker should make an extra hot chocolate with one sugar and a stick`() {
        val initialOrder = Order(code = "Hh", sugar = 1, stick = 0, amount = BigDecimal(1))
        val expectedDrink = "Drink maker will make an extra hot chocolate with one sugar and a stick"

        val drinkMaker = DrinkMaker()
        val drink = drinkMaker.makeDrinkOrForwardMessage(initialOrder)

        assertThat(drink).isEqualTo(expectedDrink)
    }

    @Test
    internal fun `drink maker should make an extra hot tea with two sugars and a stick`() {
        val initialOrder = Order(code = "Th", sugar = 2, stick = 0, amount = BigDecimal(1))
        val expectedDrink = "Drink maker will make an extra hot tea with two sugars and a stick"

        val drinkMaker = DrinkMaker()
        val drink = drinkMaker.makeDrinkOrForwardMessage(initialOrder)

        assertThat(drink).isEqualTo(expectedDrink)
    }
}