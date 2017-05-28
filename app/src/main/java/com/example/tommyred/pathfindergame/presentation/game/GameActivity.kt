package com.example.tommyred.pathfindergame.presentation.game

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast

import com.example.tommyred.pathfindergame.R

import com.example.tommyred.pathfindergame.domain.board.GameBoard
import com.example.tommyred.pathfindergame.domain.utilities.Direction
import com.example.tommyred.pathfindergame.presentation.result.ResultActivity
import nucleus.factory.RequiresPresenter
import nucleus.view.NucleusActivity

/**
 * Created by Rechtig on 23.04.2017.
 */

@RequiresPresenter(GamePresenter::class)
class GameActivity : NucleusActivity<GamePresenter>(), GameView {

    internal var gameBoardLayout: GameBoardLayout? = null
    internal var btnControl: LinearLayout? = null
    internal var gameStarters: LinearLayout? = null

    internal var controlTop: Button? = null
    internal var controlLeft: Button? = null
    internal var controlRight: Button? = null
    internal var controlDown: Button? = null

    internal var easyGame: Button? = null
    internal var mediumGame: Button? = null
    internal var hardGame: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        gameBoardLayout = findViewById(R.id.gameLayout) as GameBoardLayout
        btnControl = findViewById(R.id.btnControl) as LinearLayout
        gameStarters = findViewById(R.id.gameStarters) as LinearLayout

//        btnControl?.setVisibility(View.INVISIBLE)
//        gameBoardLayout?.setVisibility(View.INVISIBLE)

        controlTop = findViewById(R.id.control_top) as Button
        controlLeft = findViewById(R.id.control_left) as Button
        controlRight = findViewById(R.id.control_right) as Button
        controlDown = findViewById(R.id.control_down) as Button

        easyGame = findViewById(R.id.easyGame) as Button
        mediumGame = findViewById(R.id.mediumGame) as Button
        hardGame = findViewById(R.id.hardGame) as Button

        controlTop?.setOnClickListener { presenter.callForMove(Direction.TOP) }
        controlLeft?.setOnClickListener { presenter.callForMove(Direction.LEFT) }
        controlRight?.setOnClickListener { presenter.callForMove(Direction.RIGHT) }
        controlDown?.setOnClickListener { presenter.callForMove(Direction.DOWN) }

        gameStarters?.setVisibility(View.INVISIBLE)

//        easyGame?.setOnClickListener {
//            run {
//                gameStarters?.setVisibility(View.INVISIBLE)
//                btnControl?.setVisibility(View.VISIBLE)
//                gameBoardLayout?.setVisibility(View.VISIBLE)
//                presenter.createGame(this, 1)
//            }
//        }
//        mediumGame?.setOnClickListener {
//            run {
//                gameStarters?.setVisibility(View.INVISIBLE)
//                btnControl?.setVisibility(View.VISIBLE)
//                gameBoardLayout?.setVisibility(View.VISIBLE)
//                presenter.createGame(this, 2)
//            }
//        }
//        hardGame?.setOnClickListener {
//            run {
//                gameStarters?.setVisibility(View.INVISIBLE)
//                btnControl?.setVisibility(View.VISIBLE)
//                gameBoardLayout?.setVisibility(View.VISIBLE)
//                presenter.createGame(this, 3)
//            }
//        }

        presenter.createGame(this, 1)
    }

    override fun showGameBoard(gameBoard: GameBoard) {
        gameBoardLayout?.setBoard(gameBoard)
    }

    override fun warn(message: String) = Toast.makeText(this, message, Toast.LENGTH_LONG).show()


    override fun result(message: String) {
        val i = Intent(this, ResultActivity::class.java)
        i.putExtra("message", message)
        i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
        startActivity(i)
    }
}
