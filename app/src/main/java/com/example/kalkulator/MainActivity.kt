package com.example.kalkulator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import com.example.kalkulator.databinding.ActivityMainBinding
import org.mariuszgromada.math.mxparser.Expression
import java.text.DecimalFormat
class MainActivity : AppCompatActivity() {
    private lateinit var b: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        super.onCreate(savedInstanceState)
       b = ActivityMainBinding.inflate(layoutInflater)
        val view = b.root
        setContentView(view)
        val b =b;
        b.input.text = intent.getStringExtra("input")
        b.output.text = intent.getStringExtra("output")
        b.buttonPersen.setOnClickListener {
            InsertInput("%")
        }
       b.buttonClear.setOnClickListener {
           b.input.text = ""
           b.output.text = ""
        }
        b.buttonBukaKurung.setOnClickListener {
            InsertInput("(")
        }
       b.buttonTutupKurung.setOnClickListener {
           InsertInput(")")
        }
       b.buttonCroxx.setOnClickListener {
            val deleteLast = b.input.text.toString().dropLast(1)
           b.input.text = deleteLast
        }
       b.button0.setOnClickListener {
          InsertInput("0")
        }
       b.button1.setOnClickListener {
        InsertInput("1")
        }
       b.button2.setOnClickListener {
           InsertInput("2")
        }
       b.button3.setOnClickListener {
           InsertInput("3")
        }
           b.button4.setOnClickListener {
          InsertInput("4")
        }
       b.button5.setOnClickListener {
        InsertInput("5")
        }
       b.button6.setOnClickListener {
         InsertInput("6")
        }
       b.button7.setOnClickListener {
          InsertInput("7")
        }
       b.button8.setOnClickListener {
           InsertInput("8")
        }
       b.button9.setOnClickListener {
           InsertInput("9")
        }
      b.buttonDot.setOnClickListener {
            InsertInput(".")
        }
       b.buttonBagi.setOnClickListener {

            InsertInput("÷")
        }
       b.buttonKali.setOnClickListener {
            InsertInput("×")
        }

      b.buttonKurang.setOnClickListener {
           InsertInput("-")
        }
       b.buttonTambah.setOnClickListener {
            InsertInput("+")
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

        b.buttonUnary.setOnClickListener{
            val intent = Intent(this, Unary::class.java)
            intent.putExtra("input", b.input.text)
            intent.putExtra("output", b.output.text)
            startActivity(intent)
        }
    }

    private fun CekOperator(data:String):Boolean{
        val arr = listOf<String>("-","+","×","÷")
        return b.input.text.takeLast(1) !in arr
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


