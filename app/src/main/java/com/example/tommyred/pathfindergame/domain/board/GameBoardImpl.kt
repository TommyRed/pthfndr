package com.example.tommyred.pathfindergame.domain.board

import com.example.tommyred.pathfindergame.domain.entities.Entity
import com.example.tommyred.pathfindergame.domain.utilities.Coordinate
import com.example.tommyred.pathfindergame.domain.utilities.state.State
import com.example.tommyred.pathfindergame.domain.utilities.state.Success
import com.example.tommyred.pathfindergame.domain.utilities.state.errors.BoardError

/**
 * Created by Rechtig on 23.04.2017.
 */

class GameBoardImpl(val gameBoard: List<List<GameField>>) : GameBoard {

    init {
//        println("Gameboard of size ${gameBoard.size} has been created")
    }

    /**
     * Check min and max bounds
     */
    private fun checkBounds(value: Int, maxBound: Int): Boolean = value in 0..(maxBound - 1)

    /**
     * Check all possible bounds
     */
    private fun checkOffset(y: Int, x: Int): State =
            if (checkBounds(x, gameBoard.size) && checkBounds(y, gameBoard.size))
                Success.MOVE_IS_POSSIBLE
            else
                BoardError.MOVE_OUT_OF_BOUNDS

    /**
     * Check field on possible walls or enemies
     */
    private fun checkField(coordinate: Coordinate, field: GameFieldType): Boolean = gameBoard[coordinate.y][coordinate.x].type == field

    /**
     * Check entity on the field that move is being made
     */
    private fun checkEntities(coordinate: Coordinate): State =
            if (checkField(coordinate, GameFieldType.ENEMY))
                BoardError.MOVE_ON_ENEMY
            else if (checkField(coordinate, GameFieldType.OBSTACLE))
                BoardError.MOVE_ON_WALL
            else
                Success.MOVE_IS_POSSIBLE

    /**
     * Check if field is valid for player to move
     *
     * Depends on bounds and entity on the field
     */
    override fun isMovePossible(coord: Coordinate): State =
            if (checkOffset(coord.x, coord.y) is Success)
                checkEntities(coord)
            else
                checkOffset(coord.x, coord.y)

    /**
     * Update board
     */
    override fun update(coordinate: Coordinate, entity: Entity): GameBoard =
            GameBoardImpl(gameBoard.mapIndexed { y, row ->
                row.mapIndexed { x, gameField ->

                    if (gameField.type == entity.getFieldType() && !(coordinate.x == x && coordinate.y == y)) {
                        println("   @DEV ${entity.getFieldType().name} found on x: ${x}, y: ${y} ")
                        GameField(GameFieldType.BLANK)
                    } else
                        if (coordinate.x == x && coordinate.y == y) {
                            println("   @DEV setting entity ${entity.getFieldType().name} on x: ${x}, y: ${y}")
                            setEntity(entity)
                        } else
                            gameField
                }
            })

    /**
     * Set entity on desired location
     */
    private fun setEntity(entity: Entity): GameField = GameField(entity.getFieldType())


    override fun giveBoard(): List<List<GameField>> = gameBoard
}
