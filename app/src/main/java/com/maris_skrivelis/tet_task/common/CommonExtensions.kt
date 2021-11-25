package com.maris_skrivelis.tet_task.common

fun Char.toDigit(): Char {
    return when (this) {
        'A','B','C' -> '2'
        'D','E','F' -> '3'
        'G','H', 'I' -> '4'
        'J','K','L' -> '5'
        'M','N','O' -> '6'
        'P','Q','R','S' -> '7'
        'T','U','V' -> '8'
        'W','X','Y','Z' -> '9'
        else -> '1'
    }
}

fun String.toDigits(): String {
    val letters = mutableListOf<Char>()

    this.uppercase().forEach { char ->
        letters.add(char.toDigit())
    }
    return String(letters.toCharArray())
}
