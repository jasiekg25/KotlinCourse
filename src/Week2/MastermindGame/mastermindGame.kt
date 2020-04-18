package Week2.MastermindGame

fun main() {
    println(evaluateGuess("ABCD", "AACC"))
}


data class Evaluation(val rightPosition: Int, val wrongPosition: Int)

// Functional Way
fun evaluateGuess(codeMakerWord: String, codeBreakerWord: String): Evaluation {
    val rightPosition = codeMakerWord.zip(codeBreakerWord).count{ it.first == it.second }
    val commonLetters = "ABCDEF".sumBy { ch ->
        Math.min(codeMakerWord.count{ it == ch}, codeBreakerWord.count{ it == ch})
    }

    return Evaluation(
        rightPosition = rightPosition,
        wrongPosition = (commonLetters - rightPosition)
    )
}



// Longer Solution
fun evaluateGuess1(secret: String, guess: String): Evaluation {
    return Evaluation(
        rightPosition = countSameLattersOnSamePosition(secret, guess),
        wrongPosition = countCommonLetters(secret, guess) - countSameLattersOnSamePosition(secret, guess)
    )


}

//fun masterMindEvaluator(codeMakerWord: String, codeBreakerWord: String): Pair<Int, Int> {
//    val sameLettersSamePositions = countSameLattersOnSamePosition(codeMakerWord, codeBreakerWord)
//    val sameLettersNoMatterPosition = countCommonLetters(codeMakerWord, codeBreakerWord)
//    return sameLettersSamePositions to sameLettersNoMatterPosition
//}

fun countSameLattersOnSamePosition(codeMakerWord: String, codeBreakerWord: String): Int {
    var counter = 0;
    for (i in codeMakerWord.indices) {
        if (codeMakerWord[i] == codeBreakerWord[i]) counter++
    }
    return counter
}

fun countCommonLetters(codeMakerWord: String, codeBreakerWord: String): Int {
    var counter = 0
    val evaluatedLetters = mutableListOf<Char>()
    for (letter in codeMakerWord) {
        if (!evaluatedLetters.contains(letter)) {
            val codeBreakerCounter = countLettersInWord(letter, codeBreakerWord)
            val codeMakerCounter = countLettersInWord(letter, codeMakerWord)
            if (codeMakerCounter >= codeBreakerCounter) {
                counter += codeBreakerCounter
            } else {
                counter += codeMakerCounter
            }
            evaluatedLetters.add(letter)
        }
    }
    return counter
}

fun countLettersInWord(letter: Char, word: String): Int {
    var counter = 0;
    for (i in word.indices) {
        if (word[i] == letter) counter++
    }
    return counter
}

