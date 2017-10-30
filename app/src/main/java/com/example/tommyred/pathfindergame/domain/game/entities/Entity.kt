package com.example.tommyred.pathfindergame.domain.game.entities

import com.example.tommyred.pathfindergame.domain.game.board.GameFieldType
import com.example.tommyred.pathfindergame.domain.common.utilities.Coordinate

/**
 * Created by Rechtig on 25.05.2017.
 */
interface Entity {

    fun getLocation(): Coordinate

    fun getFieldType(): GameFieldType
}