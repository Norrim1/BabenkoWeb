package org.example

fun main(args: Array<String>) {
    val temp = args.groupingBy { it }.eachCount()
    println(temp)
}