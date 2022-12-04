package src

fun findCommonCharacter(compartment1: String, compartment2: String): Char {
    var char = '!'
    val charactersCompartment1 = compartment1.chunked(1)
    for (characterInCompartment in charactersCompartment1) {
        if (compartment2.contains(characterInCompartment)) {
            char = characterInCompartment[0]
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

fun calculateDay3Part1() {
    val filename = "inputs/day3.txt"
    val rucksackContents = loadPuzzleInput(filename)

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
        val char = findCommonCharacter(compartment1 = compartments[index], compartment2 = compartments[index + 1])
        totalItemPriority += getItemPriority(char = char)
        //print("\nindex is currently $index, priority for this calculation was $totalItemPriority because of character $char")
        index += 2 // EVIL MAGIC NUMBER
    }
    print("Result for Part 1 is $totalItemPriority")
}