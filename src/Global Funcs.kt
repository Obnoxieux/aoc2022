package src

import java.io.File

fun loadPuzzleInput(filename: String): List<String> {
    val inputContent = File(filename).readText()
    return inputContent.split("\n")
}