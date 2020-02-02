package com.kotlin

import org.junit.jupiter.api.Test
import java.io.FileInputStream
import java.util.*

class AppTests {

    private var filtered = filterUsers(Scanner(FileInputStream("src/test/resources/input.txt")))
    private var testUsers = listOf(
            Pair(
                    setOf("user1", "user2", "user4"),
                    setOf("xxx@ya.ru", "aaa@bbb.ru", "ups@pisem.net", "foo@gmail.com", "lol@mail.ru")
            ),
            Pair(
                    setOf("user3", "user5"),
                    setOf("vasya@pupkin.com", "xyz@pisem.net"))
    )

    @Test
    fun contextLoads() {
        testUsers.forEach { testUser ->
            assert(filtered.any { testUser.first.contains(it.key) && it.value == testUser.second })
        }
    }
}