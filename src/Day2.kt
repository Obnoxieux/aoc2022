package src

import java.io.File

//--- Day 2: Rock Paper Scissors ---//

enum class GameResult {
    WON, LOST, DRAW
}

fun getRPSGameScore(opponent: String, player: String): Int {
    var score = 0

    when (player) {
        "X" -> score += 1
        "Y" -> score += 2
        "Z" -> score += 3
        else -> {
            print("wrong values here, please disregard result")
        }
    }
    val replacedOpponent = opponent.replace("A", "Rock").replace("B", "Paper").replace("C", "Scissors")
    val replacedPlayer = player.replace("X", "Rock").replace("Y", "Paper").replace("Z", "Scissors")

    val result = determineGameResult(replacedOpponent, replacedPlayer)

    score += when (result) {
        GameResult.WON -> 6
        GameResult.LOST -> 0
        GameResult.DRAW -> 3
    }
    //print("\nTotal Score added for this round is $score, result was $result")
    return score
}

fun determineGameResult(opponent: String, player: String): GameResult {
    return if (player == opponent) {
        GameResult.DRAW
    } else if (player == "Rock" && opponent == "Scissors" || player == "Scissors" && opponent == "Paper" || player == "Paper" && opponent == "Rock") {
        GameResult.WON
    } else {
        GameResult.LOST
    }
}

fun calculateDay2() {
    val filename = "inputs/day2.txt"
    val inputContent = File(filename).readText()
    val games = inputContent.split("\n").toTypedArray()
    //println(games.contentToString())

    var totalScore = 0
    for (game in games) {
        val playersChoice = game.last().toString()
        val opponentsChoice = game.first().toString()
        totalScore += getRPSGameScore(opponentsChoice, playersChoice)
        //DEBUG
        //print("\n$totalScore")
    }
    print(totalScore)
}