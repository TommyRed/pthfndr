package com.example.tommyred.pathfindergame.domain.entities

import com.example.tommyred.pathfindergame.domain.board.GameBoard
import com.example.tommyred.pathfindergame.domain.board.GameFieldType
import com.example.tommyred.pathfindergame.domain.utilities.Coordinate
import com.example.tommyred.pathfindergame.domain.utilities.Direction
import com.example.tommyred.pathfindergame.presentation.game.GamePresenter

/**
 * Created by Rechtig on 25.05.2017.
 */
interface Entity {

    fun getLocation(): Coordinate

    fun getFieldType(): GameFieldType
}