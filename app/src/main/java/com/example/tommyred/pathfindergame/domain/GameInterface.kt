package com.example.tommyred.pathfindergame.domain

import com.example.tommyred.pathfindergame.domain.board.GameBoard
import com.example.tommyred.pathfindergame.domain.board.GameBoardImpl
import com.example.tommyred.pathfindergame.domain.utilities.Direction
import com.example.tommyred.pathfindergame.presentation.game.GamePresenter

/**
 * Created by Rechtig on 21.05.2017.
 */
interface GameInterface {

    /**
     * Fun that is called everytime direction button is pressed
     * resolves the move and reacts. Recreates gameboard.
     */
    fun onMoveMade(direction: Direction, presenter: GamePresenter): GameBoard
}