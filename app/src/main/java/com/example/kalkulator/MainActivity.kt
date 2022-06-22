package com.example.kalkulator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.mariuszgromada.math.mxparser.Expression
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    lateinit var input: TextView
    lateinit var output: TextView
    lateinit var toBeRooted: String
    lateinit var history: Array<String>
    private var counter: Int = 0
    var isRoot: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        input = findViewById(R.id.input)
        output = findViewById(R.id.output)
        history = (arrayOf("", "", "", "", "", "", "", "", "", ""))
        if (savedInstanceState != null) {
            history = savedInstanceState.getStringArray(historystrings.toString())!!;
        }
        val button0 = findViewById<Button>(R.id.zero)
        val button2 = findViewById<Button>(R.id.two)
        val button3 = findViewById<Button>(R.id.three)
        val button4 = findViewById<Button>(R.id.four)
        val button5 = findViewById<Button>(R.id.five)
        val button6 = findViewById<Button>(R.id.six)
        val button7 = findViewById<Button>(R.id.seven)
        val button8 = findViewById<Button>(R.id.eight)
        val button9 = findViewById<Button>(R.id.nine)
        val button1 = findViewById<Button>(R.id.one)
        val buttondot = findViewById<Button>(R.id.decimal)
        val buttonadd = findViewById<Button>(R.id.add)
        val buttonmin = findViewById<Button>(R.id.subtract)
        val buttonmul = findViewById<Button>(R.id.multiply)
        val buttondiv = findViewById<Button>(R.id.divide)
        val buttoneq = findViewById<Button>(R.id.equals)
        val buttonclr = findViewById<FloatingActionButton>(R.id.clear)
        val buttonroot = findViewById<Button>(R.id.root)
        val buttonpower = findViewById<Button>(R.id.power)
        val buttonhistory = findViewById<Button>(R.id.history)

        buttonclr.setOnClickListener {
            input.text = ""
            output.text = ""
        }

        buttonhistory.setOnClickListener{
            openHistory()
        }

        button0.setOnClickListener {
            input.text = addToInputText("0")
        }
        button1.setOnClickListener {
            input.text = addToInputText("1")
        }
        button2.setOnClickListener {
            input.text = addToInputText("2")
        }
        button3.setOnClickListener {
            input.text = addToInputText("3")
        }
        button4.setOnClickListener {
            input.text = addToInputText("4")
        }
        button5.setOnClickListener {
            input.text = addToInputText("5")
        }
        button6.setOnClickListener {
            input.text = addToInputText("6")
        }
        button7.setOnClickListener {
            input.text = addToInputText("7")
        }
        button8.setOnClickListener {
            input.text = addToInputText("8")
        }
        button9.setOnClickListener {
            input.text = addToInputText("9")
        }
        buttondot.setOnClickListener {
            input.text = addToInputText(".")
        }
        buttondiv.setOnClickListener {
            input.text = addToInputText("÷")
        }
        buttonmul.setOnClickListener {
            input.text = addToInputText("×")
        }

        buttonmin.setOnClickListener {
            input.text = addToInputText("-")
        }
        buttonadd.setOnClickListener {
            input.text = addToInputText("+")
        }

        buttoneq.setOnClickListener {
            showResult()
        }

        buttonroot.setOnClickListener{
            getRoot()
        }

        buttonpower.setOnClickListener{
            input.text = addToInputText("^")
        }
    }

    override fun onResume() {
        super.onResume()
        history = historystrings
        counter = counter2
    }



    fun openHistory(){
        val historyStrings: Array<String> = history
        val intent = Intent(this, HistoryActivity::class.java).apply{
            putExtra(HISTORY, historyStrings)
        }
        startActivity(intent)
    }


    private fun addToInputText(buttonValue: String): String {

        return input.text.toString() + "" + buttonValue
    }

    private fun getRoot(){
        toBeRooted = input.text.toString()
        input.text = ""
        isRoot = true
    }


    private fun getInputExpression(): String {
        if (isRoot){
            var temp = input.text.toString()
            input.text = "root("+temp+","+toBeRooted+")"

        }
        var expression = input.text.replace(Regex("÷"), "/")
        expression = expression.replace(Regex("×"), "*")
        return expression
    }

    private fun removeLast(){
        history [0] = history [1]
        history [1] = history [2]
        history [2] = history [3]
        history [3] = history [4]
        history [4] = history [5]
        history [5] = history [6]
        history [6] = history [7]
        history [7] = history [8]
        history [8] = history [9]
    }

    private fun showResult() {
        try {
            val expression = getInputExpression()
            val result = Expression(expression).calculate()
            if (result.isNaN()) {
                output.text = "ERR"
                output.setTextColor(ContextCompat.getColor(this, R.color.red))
            } else {
                output.text = DecimalFormat("0.######").format(result).toString()
                input.text = output.text
                output.setTextColor(ContextCompat.getColor(this, R.color.white))
                if(counter <= 9) {
                    history[counter] = output.text.toString()
                    counter += 1
                }
                else {
                    removeLast()
                    counter -= 1
                    history [counter] = output.text.toString()
                }
            }
        } catch (e: Exception) {
            output.text = e.toString()
            output.setTextColor(ContextCompat.getColor(this, R.color.red))
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState?.run {
            putStringArray(historystrings.toString(), history)
            counter2 = counter
        }
        // call superclass to save any view hierarchy
        super.onSaveInstanceState(outState)
    }

    companion object {
        const val HISTORY = "com.example.kalkulator.HistoryActivity"
        var historystrings = (arrayOf<String>("","","","","","","","","",""))
        var counter2: Int = 0
    }
}
