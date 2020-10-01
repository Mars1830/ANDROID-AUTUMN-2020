package com.example.converter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun keyboardClicked(num: Int) {
        val a = num
    }

    companion object {

        public enum class KeyboardValue(value: Int) {
            DEL(10), ENTER(11)
        }
    }

}