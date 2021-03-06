package com.example.tommyred.pathfindergame.domain

import com.example.tommyred.pathfindergame.domain.game.board.GameBoard
import com.example.tommyred.pathfindergame.domain.game.entities.Enemy
import com.example.tommyred.pathfindergame.domain.game.entities.Player
import com.example.tommyred.pathfindergame.domain.common.utilities.Coordinate
import com.example.tommyred.pathfindergame.domain.common.utilities.Direction
import com.example.tommyred.pathfindergame.presentation.game.GamePresenter

/**
 * Created by Rechtig on 23.04.2017.
 */
class Game constructor(var board: GameBoard, val player: Player, val enemy: Enemy, val coinPos: Coordinate) : GameInterface {

    init {
        board = board.update(player.coordinate, player).update(enemy.coordinate, enemy)
    }

    override fun onMoveMade(direction: Direction, presenter: GamePresenter): GameBoard {
        if (enemy.getLocation() == player.getLocation()) {
            presenter.result()
            return board
        } else {
            player.makeMove(direction, board, presenter)
            enemy.makeMove(board, player.getLocation())


            board = board.update(enemy.coordinate, enemy)
            board = board.update(player.coordinate, player)

            if(enemy.getLocation() == player.getLocation()) presenter.result()

            return board
        }
    }
}

