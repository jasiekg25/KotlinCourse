package Week3

import kotlin.random.Random


val foo: Int
    get() = Random.nextInt(0, 100)

fun main(args: Array<String>) {
    // The values should be different:
    println(foo)
    println(foo)
    println(foo)
}
