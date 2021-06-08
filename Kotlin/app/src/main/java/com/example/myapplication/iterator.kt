package com.example.Kotlin

interface iterator {
    open operator fun hasNext(): Boolean
    open operator fun next(): Any?
    open fun remove()
}