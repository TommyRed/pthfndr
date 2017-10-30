package com.example.tommyred.pathfindergame.mock

import com.example.tommyred.pathfindergame.domain.game.board.GameField
import com.example.tommyred.pathfindergame.domain.game.board.GameFieldType
import com.example.tommyred.pathfindergame.domain.common.utilities.Coordinate

/**
 * Created by Rechtig on 23.04.2017.
 */

object Mock {

  val playerPos: Coordinate = Coordinate(0, 0)
  val enemyPos: Coordinate = Coordinate(2, 2)

  fun getMockGameBoard(): List<List<GameField>> =
          listOf(
                  listOf(GameField(GameFieldType.BLANK), GameField(GameFieldType.BLANK), GameField(GameFieldType.BLANK)),
                  listOf(GameField(GameFieldType.BLANK), GameField(GameFieldType.OBSTACLE), GameField(GameFieldType.BLANK)),
                  listOf(GameField(GameFieldType.BLANK), GameField(GameFieldType.OBSTACLE), GameField(GameFieldType.BLANK))
          )

  fun getDefaultMarkedMap(): List<List<Int>> = listOf(
          listOf(100, 100, 100),
          listOf(100, 100, 100),
          listOf(100, 100, 100)
  )

  fun getPathMap(): List<List<Int>> = listOf(
          listOf(4, 3, 2),
          listOf(3, 2, 1),
          listOf(2, 1, 0)
  )

  fun succMe(): List<Coordinate> = listOf(
          Coordinate(2, 1),
          Coordinate(2, 0),
          Coordinate(1, 0),
          Coordinate(0, 0)
  )
}
