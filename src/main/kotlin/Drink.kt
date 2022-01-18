import java.math.BigDecimal

class Drink(var code: String) {
    private var type: DrinkType = DrinkType.getDrinkTypeByCode(code)

    fun isADrinkWithoutSugarAndStick() = type == DrinkType.ORANGE_JUICE

    fun hasEnoughMoneyForDrink(amount: BigDecimal) = amount >= type.price

    fun isAnExtraHotDrink() = code.length == 2 && code.endsWith("h")

    fun getPrice(): BigDecimal = type.getRoundedPrice()

    fun formattedName() = type.name.replace("_", " ").lowercase()
}