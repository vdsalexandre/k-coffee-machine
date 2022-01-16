import java.math.BigDecimal
import java.math.RoundingMode

class DrinkMaker(private var orders: MutableList<Order> = mutableListOf()) {

    private companion object {
        const val DRINK_MAKER_COMMAND = "Drink maker will make"
        const val DRINK_MAKER_MESSAGE = "Drink maker receives a message:"
    }

    fun makeDrinkOrForwardMessage(order: Order): String {
        return if (order.code == "M") {
            "$DRINK_MAKER_MESSAGE ${order.message}"
        } else {
            val drink: Drink
            val aDrink: String

            if (isAnExtraHotDrink(order.code)) {
                drink = getDrink(getClassicDrinkCode(order.code))
                aDrink = "an extra hot"
            } else {
                drink = getDrink(order.code)
                aDrink = "one"
            }

            if (hasEnoughMoneyForDrink(drink, order.amount)) {
                orders.add(order)
                val howManySugars = getHowManySugars(order.sugar)
                val stickOrNot = getStickOrNot(order.stick)

                if (isADrinkWithoutSugarAndStick(drink)) {
                    "$DRINK_MAKER_COMMAND $aDrink ${formattedDrinkName(drink)}"
                } else
                    "$DRINK_MAKER_COMMAND $aDrink ${formattedDrinkName(drink)} with $howManySugars and $stickOrNot"
            } else {
                val amountMissing: BigDecimal = (drink.price - order.amount).setScale(2, RoundingMode.CEILING)
                "$DRINK_MAKER_MESSAGE Not enough money for ${formattedDrinkName(drink)} (missing $amountMissing €)"
            }
        }
    }

    fun getTotalAmount(): BigDecimal = orders.map { getDrinkPrice(it.code) }.sumOf { it }

    private fun getDrinkPrice(code: String) = getDrink(getClassicDrinkCode(code)).price.setScale(2, RoundingMode.HALF_UP)

    fun showOrderHistory() {
        orders.forEachIndexed { index, order -> println("Order_${index + 1}# ${getPrintDrinkName(order.code)} - ${getDrinkPrice(order.code)} €") }
        println("-----------------------------------")
        println("Total amount: ${getTotalAmount()} €")
    }

    private fun getPrintDrinkName(code: String): String {
        return if (isAnExtraHotDrink(code))
            "extra hot ${formattedDrinkName(getDrink(getClassicDrinkCode(code)))}"
        else
            formattedDrinkName(getDrink(code))
    }

    private fun getClassicDrinkCode(code: String) = code.replace("h", "")

    private fun formattedDrinkName(drink: Drink) = drink.name.replace("_", " ").lowercase()

    private fun getHowManySugars(code: Int?): String {
        return code?.let {
            when (it) {
                1 -> "one sugar"
                2 -> "two sugars"
                3 -> "three sugars"
                else -> "no sugar"
            }
        } ?: "no sugar"
    }

    private fun isAnExtraHotDrink(code: String) = code.length == 2 && code.endsWith("h")

    private fun isADrinkWithoutSugarAndStick(drink: Drink) = drink == Drink.ORANGE_JUICE

    private fun hasEnoughMoneyForDrink(drink: Drink, amount: BigDecimal) = amount >= drink.price

    private fun getDrink(code: String) = Drink.values().find { it.code == code } ?: Drink.UNKNOWN

    private fun getStickOrNot(code: Int?) = code?.let { "a stick" } ?: "no stick"
}
