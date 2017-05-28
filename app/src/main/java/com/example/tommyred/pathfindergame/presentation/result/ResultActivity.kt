package com.example.tommyred.pathfindergame.presentation.result

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.example.tommyred.pathfindergame.R

/**
 * Created by Rechtig on 28.05.2017.
 */
class ResultActivity: AppCompatActivity() {

    var resultMessage : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val result: String = intent.getStringExtra("message")

        resultMessage = findViewById(R.id.resultMessage) as TextView

        resultMessage?.setText(result)
    }
}