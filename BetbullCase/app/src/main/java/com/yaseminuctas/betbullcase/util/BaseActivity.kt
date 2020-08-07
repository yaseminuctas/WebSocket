package com.yaseminuctas.betbullcase.util

import androidx.appcompat.app.AppCompatActivity

open class BaseActivity: AppCompatActivity(){

    fun String.intOrString(): Any {
        val v = toIntOrNull()
        return when(v) {
            null -> this
            else -> v
        }
    }
}