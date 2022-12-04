package src

fun findCommonCharacter(compartments: List<String>): Char {
    var char = '!'
    val charactersCompartment1 = compartments[0].chunked(1)
    val lastIndex = compartments.size - 1

    // this is not a really complete solution - only works with lists of length 2 and 3, not arbitrary length
    for (character in charactersCompartment1) {
        if (compartments[1].contains(character) && compartments[lastIndex].contains(character)) {
            char = character[0]
            break
        }
    }
    return char
}

fun getItemPriority(char: Char): Int {
    val allCharacters = mutableListOf<Char>()
    val numbers = (1..52).toList()

    for (i in 'a'..'z') {
        allCharacters.add(i)
    }
    for (i in 'A'..'Z') {
        allCharacters.add(i)
    }
    val map = allCharacters.zip(numbers).toMap()
    return map.getValue(char) // this throws an exception if not found, good enough for this puzzle
}

fun calculateDay3Part1(rucksackContents: List<String>) {
    val compartments = mutableListOf<String>()
    for (rucksack in rucksackContents) {
        val chunks = rucksack.chunked(rucksack.length / 2)
        for (chunk in chunks) {
            compartments.add(chunk)
        }
    }

    var index = 0
    var totalItemPriority = 0
    while (index + 1 < compartments.size) {
        val char = findCommonCharacter(listOf(compartments[index], compartments[index + 1]))
        totalItemPriority += getItemPriority(char = char)
        //print("\nindex is currently $index, priority for this calculation was $totalItemPriority because of character $char")
        index += 2 // EVIL MAGIC NUMBER
    }
    print("\nResult for Part 1 is $totalItemPriority")
}

fun calculateDay3Part2(rucksackContents: List<String>) {
    var index = 0
    var totalBadgePriority = 0
    while (index + 2 <= rucksackContents.size) {
        val char = findCommonCharacter(listOf(rucksackContents[index], rucksackContents[index + 1], rucksackContents[index + 2]))
        totalBadgePriority += getItemPriority(char = char)
        print("\nindex is currently $index, priority for this calculation was $totalBadgePriority because of character $char")
        index += 3 // EVEN MORE EVIL MAGIC NUMBER
    }
    print("Result for Part 2 is $totalBadgePriority")
}

fun calculateDay3() {
    val filename = "inputs/day3.txt"
    val rucksackContents = loadPuzzleInput(filename)

    calculateDay3Part1(rucksackContents)
    calculateDay3Part2(rucksackContents)
}