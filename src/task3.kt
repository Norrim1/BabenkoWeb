package org.example

fun main(args: Array<String>) {
    val temp = args.distinct().sorted()
    println(temp.joinToString(" "))
}