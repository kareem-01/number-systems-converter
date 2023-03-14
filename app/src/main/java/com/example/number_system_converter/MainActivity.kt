package com.example.number_system_converter

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.widget.doOnTextChanged
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    private lateinit var textInputDecimal: TextInputEditText
    private lateinit var textInputOctal: TextInputEditText
    private lateinit var textInputHexDecimal: TextInputEditText
    private lateinit var textInputBinary: TextInputEditText
    private lateinit var clearButton: Button

    private val BINARY_BASE = 2
    private val HEXDECIMAL_BASE = 16
    private val OCTAL_BASE = 8

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initial()
        callbacks()

    }

    private fun initial() {

        textInputDecimal = findViewById(R.id.decimal)
        textInputOctal = findViewById(R.id.octal)
        textInputHexDecimal = findViewById(R.id.hexadecimal)
        textInputBinary = findViewById(R.id.binary)
        clearButton = findViewById(R.id.clearButton)

    }

    private fun clear() {
        textInputDecimal.text?.clear()
        textInputOctal.text?.clear()
        textInputHexDecimal.text?.clear()
        textInputBinary.text?.clear()

    }

    @SuppressLint("SuspiciousIndentation")
    private fun callbacks() {
        clearButton.setOnClickListener { clear() }

        textInputDecimal.doOnTextChanged { text, start, before, count ->
            if (textInputDecimal.isFocused) {
                if (text.toString().isNotEmpty()) {
                    textInputBinary.setText(decimalToBinary(text.toString()))
                    textInputHexDecimal.setText(decimalToHexadecimal(text.toString()))
                    textInputOctal.setText(decimalToOctal(text.toString()))
                } else {
                    clear()
                }
            }
        }

        textInputOctal.doOnTextChanged { text, start, before, count ->
            if (textInputOctal.isFocused) {
                if (text.toString().isNotEmpty()) {
                  val  decimal = octalToDecimal(text.toString())
                    textInputDecimal.setText(decimal)
                    textInputHexDecimal.setText(decimalToHexadecimal(decimal))
                    textInputBinary.setText(decimalToBinary(decimal))
                } else {
                    clear()
                }
            }
        }

        textInputHexDecimal.doOnTextChanged { text, start, before, count ->
            if (textInputHexDecimal.isFocused) {
                if (text.toString().isNotEmpty()) {
                    val decimal= hexDecimalToDecimal(text.toString())
                    textInputBinary.setText(decimalToBinary(decimal))
                    textInputDecimal.setText(decimal)
                    textInputOctal.setText(decimalToOctal(decimal))
                } else {
                    clear()
                }
            }
        }

        textInputBinary.doOnTextChanged { text, start, before, count ->
            if (textInputBinary.isFocused) {
                if (text.toString().isNotEmpty()) {
                    val decimal= binaryToDecimal(text.toString())
                    textInputDecimal.setText(decimal)
                    textInputHexDecimal.setText(decimalToHexadecimal(decimal))
                    textInputOctal.setText(decimalToOctal(decimal))
                } else {
                    clear()
                }
            }
        }





    }


    private fun decimalToBinary(decimal: String) : String = decimal.toInt().toString(BINARY_BASE)

    private fun decimalToHexadecimal(decimal: String): String = decimal.toInt().toString(HEXDECIMAL_BASE)

    private fun decimalToOctal(decimal: String): String = decimal.toInt().toString(OCTAL_BASE)


    private fun octalToDecimal(octal: String): String = octal.toInt(OCTAL_BASE).toString()

    private fun hexDecimalToDecimal(Hex : String):String = Hex.toInt(HEXDECIMAL_BASE).toString()

    private fun binaryToDecimal(Binary : String):String = Binary.toInt(BINARY_BASE).toString()

}
