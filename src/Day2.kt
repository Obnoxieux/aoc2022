package src

import java.io.File

//--- Day 2: Rock Paper Scissors ---//

enum class GameResult {
    WON, LOST, DRAW
}

fun getRPSGameScore(player: String, opponent: String): Int {
    var score = 0

    when (player) {
        "A" -> score += 1
        "B" -> score += 2
        "C" -> score += 3
        else -> {
            print("wrong values here, please disregard result")
        }
    }
    val replacedPlayer = player.replace("A", "Rock").replace("B", "Paper").replace("C", "Scissors")
    val replacedOpponent = opponent.replace("X", "Rock").replace("Y", "Paper").replace("Z", "Scissors")

    val result = determineGameResult(replacedPlayer, replacedOpponent)
    print("\n$result")

    score += when (result) {
        GameResult.WON -> 6
        GameResult.LOST -> 0
        GameResult.DRAW -> 3
    }
    return score
}

fun determineGameResult(player: String, opponent: String): GameResult {
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
        val playersChoice = game.first().toString()
        val opponentsChoice = game.last().toString()
        totalScore += getRPSGameScore(playersChoice, opponentsChoice)
        //DEBUG
        //print("\n$totalScore")
    }
    //print(totalScore)
    //11368 is wrong, too low
}