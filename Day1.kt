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
    print("The elf with the most calories carries $largestNumberOfCalories calories.")

    val elvesCaloriesSorted = elvesCalories.sortedDescending()
    var calorieCountTopThree = 0
    for (i in 0..2) {
        calorieCountTopThree += elvesCaloriesSorted[i]
    }
    print("\nThe top three elves are carrying a total of $calorieCountTopThree calories.")
}