package com.example.calculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private var text:TextView? = null
    var lastnumeric :Boolean = false
    var lastdot :Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        text = findViewById(R.id.text1)

    }
    fun onDigit(view: View){
         text?.append((view as Button).text)
         lastnumeric = true
         lastdot = false
    }

    fun onclean(viwe:View){
        text?.text=""
    }

    fun onDigitPoint(view: View){
        if(lastnumeric && !lastdot){
            text?.append(".")
            lastnumeric = true
            lastdot = false
        }
    }

    fun addop(view: View){
        text?.text?.let {
            if (lastnumeric && !addOperation(it.toString()))
                text?.append((view as Button).text)
            lastnumeric = false
            lastdot = false

        }
    }

    fun onequls(view: View){
        if(lastnumeric){
            var tvtext = text?.text.toString()
            var prefix = ""

            try {

                if(tvtext.startsWith("-")){
                    prefix = "-"
                    tvtext = tvtext.substring(1)
                }
                if(tvtext.contains("-")) {
                    val split = tvtext.split("-")

                    var one = split[0]
                    var two = split[1]

                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    text?.text = removezeroafterdot((one.toDouble() - two.toDouble()).toString())
                }else if(tvtext.contains("*")){
                    val split = tvtext.split("*")

                    var one = split[0]
                    var two = split[1]

                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    text?.text = removezeroafterdot((one.toDouble() * two.toDouble()).toString())
                }else if (tvtext.contains("+")){
                    val split = tvtext.split("+")

                    var one = split[0]
                    var two = split[1]

                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    text?.text = removezeroafterdot((one.toDouble() + two.toDouble()).toString())
                }else if (tvtext.contains("/")){
                    val split = tvtext.split("/")

                    var one = split[0]
                    var two = split[1]

                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    text?.text = removezeroafterdot((one.toDouble() / two.toDouble()).toString())
                }
            }catch (e: java.lang.ArithmeticException){
             e.printStackTrace()


            }
        }
    }

    fun removezeroafterdot(result: String): String {
        var value = result
        if(result.contains(".0"))
            value = result.substring(0,result.length-2)
        return value
    }
    fun addOperation(value: String): Boolean {
        return if(value.startsWith("-")){
            false
        }else{
            value.contains("+")
                    ||value.contains("-")
                    ||value.contains("*")
                    ||value.contains("/")
        }
    }
}