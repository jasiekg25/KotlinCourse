package Week5.gameOf15

import kotlin.math.absoluteValue

interface GameOfFifteenInitializer {
    /*
     * Even permutation of numbers 1..15
     * used to initialized the first 15 cells on a board.
     * The last cell is empty.
     */
    val initialPermutation: List<Int>
}

class RandomGameInitializer : GameOfFifteenInitializer {
    override val initialPermutation by lazy {
        val permutationList = (1 .. 15).shuffled().toMutableList()
        while (!isEven(permutationList)){
            var randomIndex = kotlin.random.Random.nextInt().absoluteValue % 15

            var tmp = permutationList[randomIndex]
            permutationList[randomIndex] = permutationList[0]
            permutationList[0] = tmp
        }
        return@lazy permutationList.toList()
    }
}

