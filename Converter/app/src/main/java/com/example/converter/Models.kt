package com.example.converter

import kotlin.math.pow
import kotlin.math.round

fun round(num : Double, digits: Int) : Double {
    val pow = 10.0.pow(digits)
    return round(num * pow) / pow
}

object Distance {

    val mmBase = 1.0
    val mBase = 10.0
    val kmBase = 10000.0

    fun mm(num : Double, pow: Int) : Double {
        return round(num, 7)
    }

    fun m(num : Double, pow: Int) : Double {
        return round(num * mBase.pow(pow), 7)
    }

    fun km(num : Double, pow: Int) : Double {
        return round(num * kmBase.pow(pow), 7)
    }
}