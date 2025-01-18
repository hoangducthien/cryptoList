package com.example.domain.common

fun String?.safe(): String {
    return this ?: ""
}

fun Int?.safe(): Int {
    return this ?: 0
}