package com.myexample.mybingokotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var historyView: TextView
    private lateinit var numberView: TextView
    private lateinit var button: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        historyView = findViewById(R.id.historyView)
        numberView = findViewById(R.id.numberView)
        button = findViewById(R.id.button)

        val viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        button.setOnClickListener{ _ ->
            viewModel.pickNextNumber()
        }

        viewModel.getState().observe(this, Observer {
            numberView.text = "" + it.nextNumber

            historyView.setText(it.historyText)

            if (it.isAllPicked) {
                button.isEnabled = false
            }
        })
    }
}
