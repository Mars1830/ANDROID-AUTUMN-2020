package com.example.converter

import android.util.Log
import androidx.arch.core.util.Function
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.lang.Exception
import kotlin.properties.Delegates
import kotlin.reflect.KClass
import kotlin.reflect.KFunction


class MainViewModel : ViewModel() {
    val sourceStr = MutableLiveData("0")
    var sourceVal  = 0.0
    var resVal = 0.0
    var category = ""
    var sourceUnit = ""
    var destUnit = ""

    fun calculateResult() : Double {
        val (sourceFun, destFun) = setFunctions()
        resVal = sourceFun.call(destFun.call(sourceVal, -1), 1)
        return resVal
    }

    private fun setFunctions() : Pair<KFunction<Double>, KFunction<Double> > {
        when (category) {
            "Distance" -> { return Pair(findFunDistance(sourceUnit), findFunDistance(destUnit)) }


            else -> { return Pair(findFunDistance(sourceUnit), findFunDistance(destUnit)) }
        }
    }

    private fun findFunDistance(unit : String) : KFunction<Double> {
        val cl = Distance
        return when(unit) {
            "Millimetres" -> cl::mm
            "Metres" -> cl::m
            "Kilometres" -> cl::km
            else -> cl::mm
        }
    }

    fun keyboardClicked(num: Int) {
        var str : String? = sourceStr.value
        when (num) {
            in 0..9 -> str += num.toString()
            10 -> str = deleteChar(str)
            11 -> print("")
        }
        updateVal(str)
        sourceStr.value = str
    }

    private fun deleteChar(str: String?): String {
        val len = str?.length
        return when (len) {
            null, 0, 1 -> "0"
            else -> str.substring(0..(len - 2))
        }
    }

    private fun updateVal(str : String?) {
        sourceVal = when (str) {
            null, "" -> 0.0
            else -> str.toDouble()
        }
    }
}