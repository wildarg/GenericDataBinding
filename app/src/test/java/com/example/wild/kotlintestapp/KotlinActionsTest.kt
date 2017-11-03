package com.example.wild.kotlintestapp

import org.junit.Test

class GreetingsData(val name: String)

interface GreetingsDataHolder {
    fun greetingsData(): GreetingsData
}

class GreetingsManager {
    fun hi(holder: GreetingsDataHolder) =
            println("Hello ${holder.greetingsData().name}")
    fun hi2(block: () -> GreetingsData) =
            hi(object : GreetingsDataHolder {
                override fun greetingsData() = block()
            })
}


class KotlinActionsTest {

    fun wild(): GreetingsData = GreetingsData("Wild")

    @Test
    fun test() {
        val mgr = GreetingsManager()
        mgr.hi2(this::wild)
    }

}