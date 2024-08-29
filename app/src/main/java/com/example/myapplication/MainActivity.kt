package com.example.myapplication

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var txtFormula: TextView
    private lateinit var txtHasil: TextView
    private var operator: String = ""
    private var firstNumber: Double = 0.0
    private var secondNumber: Double = 0.0
    private var isOperatorSelected: Boolean = false

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtFormula = findViewById(R.id.txtFormula)
        txtHasil = findViewById(R.id.txtHasil)

        findViewById<Button>(R.id.buttonReset).setOnClickListener { reset() }
        findViewById<Button>(R.id.buttonDelete).setOnClickListener { deleteLast() }
        findViewById<Button>(R.id.buttonPercent).setOnClickListener { appendOperator("%") }
        findViewById<Button>(R.id.buttonDivision).setOnClickListener { appendOperator("/") }
        findViewById<Button>(R.id.buttonMultiplication).setOnClickListener { appendOperator("*") }
        findViewById<Button>(R.id.buttonSubstraction).setOnClickListener { appendOperator("-") }
        findViewById<Button>(R.id.buttonAddition).setOnClickListener { appendOperator("+") }
        findViewById<Button>(R.id.buttonAmount).setOnClickListener { calculate() }

        // Setup number buttons
        val numberButtons = listOf(
            R.id.button0, R.id.button1, R.id.button2, R.id.button3,
            R.id.button4, R.id.button5, R.id.button6, R.id.button7,
            R.id.button8, R.id.button9
        )

        for (id in numberButtons) {
            findViewById<Button>(id).setOnClickListener { appendNumber((it as Button).text.toString()) }
        }

        findViewById<Button>(R.id.buttonPoint).setOnClickListener { appendNumber(".") }
    }

    private fun appendNumber(number: String) {
        if (isOperatorSelected) {
            txtHasil.text = number
            isOperatorSelected = false
        } else {
            if (txtHasil.text.toString() == "0") {
                txtHasil.text = number
            } else {
                txtHasil.append(number)
            }
        }
        txtFormula.append(number)
    }

    private fun appendOperator(op: String) {
        operator = op
        firstNumber = txtHasil.text.toString().toDouble()
        txtFormula.append(" $op ")
        isOperatorSelected = true
    }

    private fun calculate() {
        secondNumber = txtHasil.text.toString().toDouble()
        val result = when (operator) {
            "+" -> firstNumber + secondNumber
            "-" -> firstNumber - secondNumber
            "*" -> firstNumber * secondNumber
            "/" -> firstNumber / secondNumber
            "%" -> firstNumber *0.01
            else -> 0.0
        }
        txtHasil.text = result.toString()
        txtFormula.text = ""
    }

    private fun reset() {
        txtHasil.text = "0"
        txtFormula.text = ""
        firstNumber = 0.0
        secondNumber = 0.0
        operator = ""
        isOperatorSelected = false
    }

    private fun deleteLast() {
        val currentText = txtHasil.text.toString()
        if (currentText.isNotEmpty()) {
            txtHasil.text = currentText.dropLast(1)
            txtFormula.text = txtFormula.text.toString().dropLast(1)
        }
        if (txtHasil.text.isEmpty()) {
            txtHasil.text = "0"
        }
    }

}

