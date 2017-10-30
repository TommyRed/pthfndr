package com.example.tommyred.pathfindergame.domain.game.entities

import com.example.tommyred.pathfindergame.domain.game.board.GameBoard
import com.example.tommyred.pathfindergame.domain.game.board.GameFieldType
import com.example.tommyred.pathfindergame.domain.common.utilities.Coordinate
import com.example.tommyred.pathfindergame.domain.common.utilities.Direction
import com.example.tommyred.pathfindergame.domain.common.utilities.state.Error
import com.example.tommyred.pathfindergame.domain.common.utilities.state.Success
import com.example.tommyred.pathfindergame.presentation.game.GamePresenter

/**
 * Created by Rechtig on 21.05.2017.
 */
class Player(var coordinate: Coordinate, val type: GameFieldType) : Entity {

    init {
//        println("Player on location x: ${coordinate.x}, y: ${coordinate.y}")
    }

    /**
     *  Make move based on direction
     */
    fun makeMove(direction: Direction, gameboard: GameBoard, presenter: GamePresenter): Unit {

        println("Player on location x: ${coordinate.x}, y: ${coordinate.y}")

        val coord: Coordinate = when (direction) {
            Direction.TOP -> Coordinate(coordinate.x, coordinate.y - 1)
            Direction.LEFT -> Coordinate(coordinate.x - 1, coordinate.y)
            Direction.RIGHT -> Coordinate(coordinate.x + 1, coordinate.y)
            Direction.DOWN -> Coordinate(coordinate.x, coordinate.y + 1)
        }

        val state = gameboard.isMovePossible(coord)

        if (state is Success)
            coordinate = coord
        else {
            val error: Error = state as Error
            presenter.showMessage(error.getMessage())
        }
    }

    override fun getLocation(): Coordinate = coordinate

    override fun getFieldType(): GameFieldType = type
}