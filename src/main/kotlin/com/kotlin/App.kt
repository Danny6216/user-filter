package com.kotlin

import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.HashSet

open class App {
    companion object {
        @JvmStatic fun main(args: Array<String>) {
            println("input params:")
            filterUsers(Scanner(System.`in`)).forEach {
                println( "${it.key} -> ${it.value}")
            }
        }
    }
}

fun filterUsers(scanner: Scanner) : Map<String, Set<String>> {

    val userMailMap = HashMap<HashSet<String>, HashSet<String>>()
    while (scanner.hasNextLine()) {
        val input = scanner.nextLine()
                .replace(" ", "")
                .split("->")
        if (input.size > 1) {
            val user = input[0]
            val emails = input[1].split(",")

            emails.forEach { email ->
                if (userMailMap.isEmpty()) {
                    userMailMap[hashSetOf(user)] = hashSetOf(email)
                } else {
                    userMailMap
                            .filter { it.value.contains(email) || it.key.contains(user) }
                            .forEach {
                                it.key.add(user)
                                it.value.add(email)
                            }
                }
            }

            emails.filter { email ->
                !userMailMap.keys.any { it.contains(user) } && !userMailMap.values.any { it.contains(email) }
            }.forEach { userMailMap[hashSetOf(user)] = HashSet(emails) }
        } else println("empty input!")
    }
    return userMailMap.asSequence()
            .associate {
                it.key.first() to it.value
            }
}