package com.example.converter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.converter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun keyboardClicked(num: Int) {
        val fragmentMain = supportFragmentManager.findFragmentById(R.id.fragment_main) as MainFragment
        var str: String = fragmentMain.getInputText()

        when (num) {
            in 0..9 -> str += num.toString()
            10 -> str = DeleteChar(str)
            11 -> print("")
        }
        fragmentMain.setInputText(str)
    }

    private fun DeleteChar(str: String): String {
        val len = str.length
        when (len) {
            0, 1 -> return ""
            else -> return str.substring(0..(len - 2))
        }
    }

    companion object {

        public enum class KeyboardValue(value: Int) {
            DEL(10), ENTER(11)
        }
    }

}