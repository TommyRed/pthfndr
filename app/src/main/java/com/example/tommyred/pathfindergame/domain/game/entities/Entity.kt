package com.example.tommyred.pathfindergame.domain.entities

import com.example.tommyred.pathfindergame.domain.board.GameFieldType
import com.example.tommyred.pathfindergame.domain.common.utilities.Coordinate

/**
 * Created by Rechtig on 25.05.2017.
 */
interface Entity {

    fun getLocation(): Coordinate

    fun getFieldType(): GameFieldType
}