package com.example.week3

import android.os.Bundle
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.linear_layout)
        //setContentView(R.layout.constraint_layout)
        //setContentView(R.layout.absolute_layout)
        //setContentView(R.layout.relative_layout)
    }
}