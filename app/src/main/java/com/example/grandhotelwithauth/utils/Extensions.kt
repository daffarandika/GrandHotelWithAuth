package com.example.grandhotelwithauth.utils

fun String.capitalizeWords(): String = split(" ").map { s -> s.replaceFirstChar { c -> c.titlecase() } }.joinToString(" ")