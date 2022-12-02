import java.io.File

//--- Day 1: Calorie Counting ---//

fun main() {
    val filename = "inputs/day1.txt"
    val inputContent = File(filename).readText()
    val elfRations = inputContent.split("\n").toTypedArray()
    val elvesCalories = mutableListOf<Int>()
    var elfCalorieTotal = 0

    for (elfRation in elfRations) {
        if (elfRation.isNotEmpty()) {
            elfCalorieTotal += elfRation.toInt()
        } else {
            elvesCalories.add(elfCalorieTotal)
            elfCalorieTotal = 0
            continue
        }
    }
    val largestNumberOfCalories = elvesCalories.maxOrNull()
    print(largestNumberOfCalories)
}