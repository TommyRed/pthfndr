package com.example.tommyred.pathfindergame.domain.board

import com.example.tommyred.pathfindergame.domain.entities.Entity
import com.example.tommyred.pathfindergame.domain.utilities.Coordinate
import com.example.tommyred.pathfindergame.domain.utilities.state.State

/**
 * Created by Rechtig on 21.05.2017.
 */
interface GameBoard {

    /**
     *  Resolve if move is possible based on coordinates at the input
     */
    fun isMovePossible(coord: Coordinate): State

    /**
     * Returns gameboard ...
     * name bcs of fking kotlin funcs
     */
    fun giveBoard(): List<List<GameField>>

    /**
     * Make move
     */
    fun update(coordinate: Coordinate, entity: Entity): GameBoard
}