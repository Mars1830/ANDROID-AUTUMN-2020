package com.example.converter

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class MainViewModel : ViewModel() {
    var category = ""
    val str_from = MutableLiveData<String>()

    init {
        str_from.value = ""
    }

    fun select_from(item: String) {
        category = item
    }

    fun keyboardClicked(num: Int) {
        var str : String? = str_from.value
        when (num) {
            in 0..9 -> str += num.toString()
            10 -> str = DeleteChar(str)
            11 -> print("")
        }
        str_from.value = str
        Log.i(str, "--------------------------------------str")
    }

    private fun DeleteChar(str: String?): String? {
        val len = str?.length
        when (len) {
            null, 0, 1 -> return ""
            else -> return str.substring(0..(len - 2))
        }
    }

    fun categoryClicked(name : String) {

    }
}