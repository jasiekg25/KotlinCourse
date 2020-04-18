package Week3.task3

//Add and implement an extension function 'isEmptyOrNull()' on the type String?.
// It should return true, if the string is null or empty.

fun main(args: Array<String>) {
    val s1: String? = null
    val s2: String? = ""
    println(s1.isEmptyOrNull().equals(true))
    println(s2.isEmptyOrNull().equals(true))

    val s3 = "   "
    println(s3.isEmptyOrNull().equals(false))
}


fun String?.isEmptyOrNull(): Boolean {
    return (this?.equals("") ?: true) || (this?.equals(null) ?: true)
}
