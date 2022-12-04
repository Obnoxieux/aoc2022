package src

import java.io.File

//--- Day 2: Rock Paper Scissors ---//
//Part 2 is really tasty spaghetti, mmmhhhh

enum class GameResult {
    WON, LOST, DRAWN
}

enum class PlayerGoal {
    WIN, LOSE, DRAW
}

fun getRPSGameScore(opponent: String, player: String): Int {
    var score = 0

    val replacedOpponent = opponent.replace("A", "Rock").replace("B", "Paper").replace("C", "Scissors")
    val replacedPlayer = player.replace("X", "Rock").replace("Y", "Paper").replace("Z", "Scissors")

    when (replacedPlayer) {
        "Rock" -> score += 1
        "Paper" -> score += 2
        "Scissors" -> score += 3
        else -> {
            print("wrong values here, please disregard result")
        }
    }

    val result = determineGameResult(replacedOpponent, replacedPlayer)

    score += when (result) {
        GameResult.WON -> 6
        GameResult.LOST -> 0
        GameResult.DRAWN -> 3
    }
    //print("\nTotal Score added for this round is $score, result was $result")
    return score
}

fun determineGameResult(opponent: String, player: String): GameResult {
    return if (player == opponent) {
        GameResult.DRAWN
    } else if (player == "Rock" && opponent == "Scissors" || player == "Scissors" && opponent == "Paper" || player == "Paper" && opponent == "Rock") {
        GameResult.WON
    } else {
        GameResult.LOST
    }
}

fun determinePlayerGoal(codeword: String): PlayerGoal {
    return if (codeword == "X") {
        PlayerGoal.LOSE
    } else if (codeword == "Y") {
        PlayerGoal.DRAW
    } else {
        PlayerGoal.WIN
    }
}

fun chooseCorrectShape(goal: PlayerGoal, opponentsChoice: String): String {
    val replacedOpponent = opponentsChoice.replace("A", "Rock").replace("B", "Paper").replace("C", "Scissors")

    //trying to return the semantic values directly here, let's see if the replace func later does not fuck this up
    when (goal) {
        PlayerGoal.WIN -> {
            return when (replacedOpponent) {
                "Rock" -> "Paper"
                "Paper" -> "Scissors"
                "Scissors" -> "Rock"
                else -> {
                    "this is not a valid value"
                }
            }
        }
        PlayerGoal.LOSE -> {
            return when (replacedOpponent) {
                "Rock" -> "Scissors"
                "Paper" -> "Rock"
                "Scissors" -> "Paper"
                else -> {
                    "this is not a valid value"
                }
            }
        }
        PlayerGoal.DRAW -> {
            return when (replacedOpponent) {
                "Rock" -> "Rock"
                "Paper" -> "Paper"
                "Scissors" -> "Scissors"
                else -> {
                    "this is not a valid value"
                }
            }
        }
    }
}

fun calculateDay2Part1(games: List<String>) {
    var totalScore = 0
    for (game in games) {
        val opponentsChoice = game.first().toString()
        val playersChoice = game.last().toString()
        totalScore += getRPSGameScore(opponentsChoice, playersChoice)
    }
    print("Total Score for Part 1 is $totalScore")
}

fun calculateDay2Part2(games: List<String>) {
    var totalScore = 0
    for (game in games) {
        val opponentsChoice = game.first().toString()
        val playersCodeword = game.last().toString()
        val goal = determinePlayerGoal(codeword = playersCodeword)
        val playersChoice = chooseCorrectShape(goal = goal, opponentsChoice = opponentsChoice)
        totalScore += getRPSGameScore(opponentsChoice, playersChoice)
    }
    print("\nTotal Score for Part 2 according to strategy is $totalScore")
}

fun calculateDay2() {
    val filename = "inputs/day2.txt"
    val games = loadPuzzleInput(filename)
    calculateDay2Part1(games = games)
    calculateDay2Part2(games = games)
}