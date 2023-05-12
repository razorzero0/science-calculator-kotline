package com.example.kalkulator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import com.example.kalkulator.databinding.ActivityBitwiseBinding
import com.example.kalkulator.databinding.ActivityMainBinding
import org.mariuszgromada.math.mxparser.Expression
import java.text.DecimalFormat
class Bitwise: AppCompatActivity() {
    private lateinit var b: ActivityBitwiseBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        super.onCreate(savedInstanceState)
        b = ActivityBitwiseBinding.inflate(layoutInflater)
        val view = b.root
        setContentView(view)
        val b =b;
        b.input.text = intent.getStringExtra("input")
        b.output.text = intent.getStringExtra("output")
        b.buttonBitwiseComplement.setOnClickListener {
            InsertInput("@~")
        }
        b.buttonClear.setOnClickListener {
            b.input.text = ""
            b.output.text = ""
        }
        b.buttonBitwiseXor.setOnClickListener {
            InsertInput("@^")
        }
        b.buttonBitwiseOr.setOnClickListener {
            InsertInput("@|")
        }
        b.buttonCroxx.setOnClickListener {
            val deleteLast = b.input.text.toString().dropLast(1)
            b.input.text = deleteLast
        }
        b.buttonBitwiseAnd.setOnClickListener {
            InsertInput("@&")
        }
        b.buttonBitwiseSignedLeft.setOnClickListener {
            InsertInput("@<<")
        }
        b.buttonBitwiseSignedRight.setOnClickListener {
            InsertInput("@>>")
        }
        b.buttonBinaryEquality.setOnClickListener {
            InsertInput("=")
        }
        b.buttonBinaryDoubleEquality.setOnClickListener {
            InsertInput("==")
        }
        b.buttonBinaryInequation.setOnClickListener {
            InsertInput("≠")
        }
        b.buttonBinaryInequation2.setOnClickListener {
            InsertInput("<>")
        }
        b.buttonBinaryLowerThan.setOnClickListener {
            InsertInput("<")
        }
        b.buttonBinaryGreatherThan.setOnClickListener {
            InsertInput(">")
        }
        b.buttonBinaryLowerThanEqual.setOnClickListener {
            InsertInput("<=")
        }
        b.buttonBinaryGreatherThan.setOnClickListener {
            InsertInput(">=")
        }
        b.buttonBinaryLog.setOnClickListener {

            InsertInput("log(")
        }
        b.buttonBinaryMod.setOnClickListener {
            InsertInput("mod(")
        }

        b.buttonBinaryBinomial.setOnClickListener {
            InsertInput("C(")
        }
        b.buttonComa.setOnClickListener {
            InsertInput(",")
        }
        b.buttonHasil.setOnClickListener {
            showResult()
        }
        b.buttonMain.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("input", b.input.text)
            intent.putExtra("output", b.output.text)
            startActivity(intent)
        }

        b.buttonUnary.setOnClickListener{
            val intent = Intent(this, Unary::class.java)
            intent.putExtra("input", b.input.text)
            intent.putExtra("output", b.output.text)
            startActivity(intent)
        }
    }

    private fun CekOperator():Boolean{
        val arr = listOf<Char>('-','+','×','÷')
//        return b.input.text.takeLast(2).first() !in arr
        return true
    }



    private fun InsertInput(buttonValue: String){
        if(CekOperator()){
            b.input.text =b.input.text.toString() + "" + buttonValue
        }else{

        }

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

fun main(){
    val expression = "Pi(10)"
    val result = Expression(expression).calculate()
    println(result)
}


