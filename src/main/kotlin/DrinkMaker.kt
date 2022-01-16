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
            val drink = Drink(order.code)

            val aDrink: String = if (drink.isAnExtraHotDrink()) {
                "an extra hot"
            } else {
                "one"
            }

            if (drink.hasEnoughMoneyForDrink(order.amount)) {
                orders.add(order)
                val howManySugars = getHowManySugars(order.sugar)
                val stickOrNot = getStickOrNot(order.stick)

                if (drink.isADrinkWithoutSugarAndStick()) {
                    "$DRINK_MAKER_COMMAND $aDrink ${drink.formattedName()}"
                } else
                    "$DRINK_MAKER_COMMAND $aDrink ${drink.formattedName()} with $howManySugars and $stickOrNot"
            } else {
                val amountMissing: BigDecimal = (drink.getPrice() - order.amount).setScale(2, RoundingMode.CEILING)
                "$DRINK_MAKER_MESSAGE Not enough money for ${drink.formattedName()} (missing $amountMissing €)"
            }
        }
    }

    fun getTotalAmount(): BigDecimal = orders.map { DrinkType.getRoundedPriceByCode(it.code) }.sumOf { it }


    fun showOrderHistory() {
        orders.forEachIndexed { index, order -> println("Order_${index + 1}# ${DrinkType.valueOf(order.code).getClassicName()} - ${DrinkType.valueOf(order.code).getRoundedPrice()} €") }
        println("-----------------------------------")
        println("Total amount: ${getTotalAmount()} €")
    }

    private fun getHowManySugars(sugarValue: Int?): String {
        return sugarValue?.let {
            when (it) {
                1 -> "one sugar"
                2 -> "two sugars"
                3 -> "three sugars"
                else -> "no sugar"
            }
        } ?: "no sugar"
    }

    private fun getStickOrNot(stickValue: Int?) = stickValue?.let { "a stick" } ?: "no stick"
}
