package com.example.tommyred.pathfindergame.mock

import com.example.tommyred.pathfindergame.domain.board.GameField
import com.example.tommyred.pathfindergame.domain.board.GameFieldType
import com.example.tommyred.pathfindergame.domain.utilities.Coordinate

/**
 * Created by Rechtig on 23.04.2017.
 */

class Mock {
    public companion object {
        public fun createMockGameBoard() =
                listOf<List<GameField>>(
                        listOf(GameField(GameFieldType.BLANK), GameField(GameFieldType.BLANK), GameField(GameFieldType.BLANK)),
                        listOf(GameField(GameFieldType.BLANK), GameField(GameFieldType.OBSTACLE), GameField(GameFieldType.BLANK)),
                        listOf(GameField(GameFieldType.BLANK), GameField(GameFieldType.OBSTACLE), GameField(GameFieldType.BLANK))
                )

        fun giveHoe(): List<List<Int>> = listOf<List<Int>>(
                listOf(4,3,2),
                listOf(3,2,1),
                listOf(2,1,0)
        )

        fun succMe(): List<Coordinate> = listOf<Coordinate>(
                Coordinate(2,1),
                Coordinate(2,0),
                Coordinate(1,0),
                Coordinate(0,0)
        )
    }
}
