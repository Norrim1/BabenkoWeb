package org.example

fun main(args: Array<String>) {
    val words = if (args.isEmpty()) { System.`in`.bufferedReader().use { it.readText() }.split(Regex("\\s+")).filter { it.isNotEmpty() }
    } else args.filter { it.isNotEmpty() }

    val temp = words.groupingBy { it }.eachCount()

    val sortedEntries = temp.entries.sortedWith( compareByDescending<Map.Entry<String, Int>> { it.value }.thenBy { it.key })

    val new = sortedEntries.associate { it.toPair() }
    println(new)
}