package com.example.tommyred.pathfindergame.presentation.game


import com.example.tommyred.pathfindergame.domain.game.board.GameBoard

/**
 * Created by Rechtig on 19.03.2017.
 */

interface GameView {

    /**
     * Show gameboard on screen
     */
    fun showGameBoard(gameBoard: GameBoard)

    /**
     * Prints Toast message
     */
    fun warn(message: String)

    /**
     * Starts result activity
     */
    fun result(message: String)
}
