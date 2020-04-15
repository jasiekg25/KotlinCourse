package Week1.task2


//  Change the 'sum' function so that it was declared as an extension to List<Int>.

fun List<Int>.sum(list: List<Int> = this): Int {
    var result = 0
    for (i in list) {
        result += i
    }
    return result
}

fun main(args: Array<String>) {
    val sum = listOf<Int>(1, 2, 3).sum()
    println(sum)    // 6
}
