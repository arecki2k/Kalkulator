package com.example.kalkulator

import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import com.example.kalkulator.MainActivity.Companion.HISTORY

class HistoryActivity : AppCompatActivity() {
    private var message = emptyArray<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        message = intent.getStringArrayExtra(HISTORY) as Array<String>
        val history0 = findViewById<TextView>(R.id.history0)
        val history1 = findViewById<TextView>(R.id.history1)
        val history2 = findViewById<TextView>(R.id.history2)
        val history3 = findViewById<TextView>(R.id.history3)
        val history4 = findViewById<TextView>(R.id.history4)
        val history5 = findViewById<TextView>(R.id.history5)
        val history6 = findViewById<TextView>(R.id.history6)
        val history7 = findViewById<TextView>(R.id.history7)
        val history8 = findViewById<TextView>(R.id.history8)
        val history9 = findViewById<TextView>(R.id.history9)
        history0.text = message!![0]
        history1.text = message!![1]
        history2.text = message!![2]
        history3.text = message!![3]
        history4.text = message!![4]
        history5.text = message!![5]
        history6.text = message!![6]
        history7.text = message!![7]
        history8.text = message!![8]
        history9.text = message!![9]


    }


}