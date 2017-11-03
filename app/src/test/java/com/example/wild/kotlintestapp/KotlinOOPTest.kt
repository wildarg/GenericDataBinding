package com.example.wild.kotlintestapp

import org.junit.Test

fun log(msg: String) = println(msg)

interface MainAncestorClass {

    fun first() {
        log("first from MainAncestorClass")
        second()
    }

    fun second() {
        log("second from MainAncestorClass")
    }
}

class FirstChild : MainAncestorClass {
    override fun first() {
        log("first from FirstChild")
        second()
    }
}

class SecondChild : MainAncestorClass by FirstChild() {
    override fun second() {
        log("second from SecondChild")
    }
}

class KotlinOOPTest {
    @Test
    fun test() {
        val f: MainAncestorClass = FirstChild()
        f.first()
        log("-------------------")
        val s: SecondChild = SecondChild()
        s.first()
    }
}