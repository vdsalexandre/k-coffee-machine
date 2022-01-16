import java.math.BigDecimal

class Drink(private var code: String, var type: DrinkType = DrinkType.UNKNOWN) {

    constructor(code: String) : this() {
        this.code = code
        this.type = DrinkType.valueOf(code.replace("h", ""))
    }

    fun isADrinkWithoutSugarAndStick() = type == DrinkType.ORANGE_JUICE

    fun hasEnoughMoneyForDrink(amount: BigDecimal) = amount >= type.price

    fun isAnExtraHotDrink() = code.length == 2 && code.endsWith("h")

    fun getPrice(): BigDecimal = type.getRoundedPrice()

    fun formattedName() = type.name.replace("_", " ").lowercase()

}