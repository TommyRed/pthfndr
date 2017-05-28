package com.example.tommyred.pathfindergame.presentation

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

import com.example.tommyred.pathfindergame.R
import com.example.tommyred.pathfindergame.presentation.game.GameActivity

class MainActivity : AppCompatActivity() {

    var btn: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn = findViewById(R.id.startGame) as Button

        btn?.setOnClickListener { start() }
    }

    fun start() {
        val i = Intent(this, GameActivity::class.java)
        i.flags = i.flags or Intent.FLAG_ACTIVITY_NO_HISTORY
        startActivity(i)
    }
}
