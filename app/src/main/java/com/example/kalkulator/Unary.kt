package com.example.kalkulator

import com.example.kalkulator.databinding.ActivityUnaryBinding
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import com.example.kalkulator.databinding.ActivityBitwiseBinding
import org.mariuszgromada.math.mxparser.Expression
import java.text.DecimalFormat
class Unary : AppCompatActivity() {
    private lateinit var b: ActivityUnaryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        super.onCreate(savedInstanceState)
        b = ActivityUnaryBinding.inflate(layoutInflater)
        val view = b.root
        setContentView(view)
        val b =b;

        b.input.text = intent.getStringExtra("input")
        b.output.text = intent.getStringExtra("output")
        b.buttonTrigonometriSin.setOnClickListener {
            InsertInput("sin(")
        }
        b.buttonClear.setOnClickListener {
            b.input.text = ""
            b.output.text = ""
        }
        b.buttonTrigonometriCos.setOnClickListener {
            InsertInput("cos(")
        }
        b.buttonTrigonometriTangent.setOnClickListener {
            InsertInput("tan(")
        }
        b.buttonCroxx.setOnClickListener {
            val deleteLast = b.input.text.toString().dropLast(1)
            b.input.text = deleteLast
        }
        b.buttonTrigonometriTan.setOnClickListener {
            InsertInput("tan(")
        }
        b.buttonTrigonometriCotangent.setOnClickListener {
            InsertInput("cot(")
        }
        b.buttonTrigonometriSecant.setOnClickListener {
            InsertInput("sec(")
        }
        b.buttonTrigonometriCosecant.setOnClickListener {
            InsertInput("cosec(")
        }
        b.buttonTrigonometriInversSine.setOnClickListener {
            InsertInput("asin(")
        }
        b.buttonTrigonometriInversCosin.setOnClickListener {
            InsertInput("acos(")
        }
        b.buttonTrigonometriInversTangent.setOnClickListener {
            InsertInput("atg(")
        }
        b.buttonTrigonometriInversCotangent.setOnClickListener {
            InsertInput("actg(")
        }
        b.buttonNaturalLogarithm.setOnClickListener {
            InsertInput("ln(")
        }
        b.buttonBinaryLogarithm2.setOnClickListener {
            InsertInput("log(")
        }
        b.buttonBinaryLogarithm2.setOnClickListener {
            InsertInput("log2(")
        }
        b.buttonDegreeToRadians.setOnClickListener {

            InsertInput("rad(")
        }
        b.buttonRadiansToDegree.setOnClickListener {
            InsertInput("deg(")
        }

        b.buttonPi.setOnClickListener {
            InsertInput("Pi(")
        }
        b.buttonSigma.setOnClickListener {
            InsertInput("∑(")
        }
        b.buttonComa.setOnClickListener {
            InsertInput(",")
        }
        b.buttonHasil.setOnClickListener {
            showResult()
        }
        b.buttonBitwise.setOnClickListener{
            val intent = Intent(this, Bitwise::class.java)
            intent.putExtra("input", b.input.text)
            intent.putExtra("output", b.output.text)
            startActivity(intent)
        }

        b.buttonMain.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("input", b.input.text)
            intent.putExtra("output", b.output.text)
            startActivity(intent)
        }
    }



    private fun InsertInput(buttonValue: String){

            b.input.text =b.input.text.toString() + "" + buttonValue


    }
    private fun getInputExpression(): String {
        var expression =b.input.text.replace(Regex("÷"), "/")
        expression = expression.replace(Regex("×"), "*")
        return expression
    }
    private fun showResult() {
        try {
            val expression = getInputExpression()
            val result = Expression(expression).calculate()
            if (result.isNaN()) {
                b.input.text = ""
                b.output.text = ""
            } else {
                // Show hasil
                b.output.text = DecimalFormat("0.######").format(result).toString()
            }
        } catch (e: Exception) {
            b.output.text = ""
        }
    }



}

