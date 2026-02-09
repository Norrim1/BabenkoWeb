package org.example

fun main(args: Array<String>) {
    val temp = args.groupingBy { it }.eachCount()

    val sortedEntries = temp.entries.sortedWith( compareByDescending<Map.Entry<String, Int>> { it.value }.thenBy { it.key })

    val new = sortedEntries.associate { it.toPair() }
    println(new)
}