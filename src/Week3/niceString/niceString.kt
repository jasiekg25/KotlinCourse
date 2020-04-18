package Week3.niceString

fun main() {
    println("bac".isNice())
    println("aza".isNice())
    println("abaca".isNice())
    println("baaa".isNice())
    println("aaab".isNice())
}

fun String.isNice(): Boolean {
    fun Boolean.toInt(): Int = if (this) 1 else 0
    fun Char.doubleChar(): String = "$this$this"

    fun notContainsBadWords() =
        listOf<String>("ba", "bu", "be").none { contains(it) }

    fun containsMoreThan3Vawels(): Boolean =
        count { letter -> letter in "aeiou" } >= 3

    fun containsDoubleLetter(): Boolean =
        zipWithNext().any { it.first == it.second }
//        this.sumBy{ ch -> this.contains(ch.doubleChar()).toInt() } > 0


    return listOf(notContainsBadWords(), containsMoreThan3Vawels(), containsDoubleLetter())
        .count { it } > 1
}
