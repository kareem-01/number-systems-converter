package com.example.number_system_converter

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.widget.doOnTextChanged
//import androidx.core.widget.addTextChangedListener
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    private lateinit var decimalNumber: TextInputEditText
    private lateinit var octalNumber: TextInputEditText
    private lateinit var hexDecimalNumber: TextInputEditText
    private lateinit var binaryNumber: TextInputEditText
    private lateinit var clearButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initial()
        callbacks()

    }

    private fun initial() {

        decimalNumber = findViewById(R.id.decimal)
        octalNumber = findViewById(R.id.octal)
        hexDecimalNumber = findViewById(R.id.hexadecimal)
        binaryNumber = findViewById(R.id.binary)
        clearButton = findViewById(R.id.clearButton)

    }

    private fun clear() {
        decimalNumber.text?.clear()
        octalNumber.text?.clear()
        hexDecimalNumber.text?.clear()
        binaryNumber.text?.clear()

    }

    @SuppressLint("SuspiciousIndentation")
    private fun callbacks() {
        clearButton.setOnClickListener { clear() }

        decimalNumber.doOnTextChanged { text, start, before, count ->
            if (decimalNumber.isFocused) {
                if (text.toString().isNotEmpty()) {
                    binaryNumber.setText(decimalToBinary(text.toString()))
                    hexDecimalNumber.setText(decimalToHexadecimal(text.toString()))
                    octalNumber.setText(decimalToOctal(text.toString()))
                } else {
                    clear()
                }
            }
        }

        octalNumber.doOnTextChanged { text, start, before, count ->
            if (octalNumber.isFocused) {
                if (text.toString().isNotEmpty()) {
                  val  decimal = octalToDecimal(text.toString())
                    decimalNumber.setText(decimal)
                    hexDecimalNumber.setText(decimalToHexadecimal(decimal))
                    binaryNumber.setText(decimalToBinary(decimal))
                } else {
                    clear()
                }
            }
        }

        hexDecimalNumber.doOnTextChanged { text, start, before, count ->
            if (hexDecimalNumber.isFocused) {
                if (text.toString().isNotEmpty()) {
                    val decimal= hexDecimalToDecimal(text.toString())
                    binaryNumber.setText(decimalToBinary(decimal))
                    decimalNumber.setText(decimal)
                    octalNumber.setText(decimalToOctal(decimal))
                } else {
                    clear()
                }
            }
        }

        binaryNumber.doOnTextChanged { text, start, before, count ->
            if (binaryNumber.isFocused) {
                if (text.toString().isNotEmpty()) {
                    val decimal= binaryToDecimal(text.toString())
                    decimalNumber.setText(decimal)
                    hexDecimalNumber.setText(decimalToHexadecimal(decimal))
                    octalNumber.setText(decimalToOctal(decimal))
                } else {
                    clear()
                }
            }
        }





    }


    private fun decimalToBinary(decimal: String) : String = decimal.toInt().toString(2)

    private fun decimalToHexadecimal(decimal: String): String = decimal.toInt().toString(16)

    private fun decimalToOctal(decimal: String): String = decimal.toInt().toString(8)


    fun octalToDecimal(octal: String): String = octal.toInt(8).toString()

    fun hexDecimalToDecimal(Hex : String):String = Hex.toInt(16).toString()

    fun binaryToDecimal(Binary : String):String = Binary.toInt(2).toString()

}
