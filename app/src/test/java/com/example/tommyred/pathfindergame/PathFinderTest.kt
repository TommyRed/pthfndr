package com.example.tommyred.pathfindergame

import com.example.tommyred.pathfindergame.domain.game.board.GameField
import com.example.tommyred.pathfindergame.domain.pathfinder.PathFinder
import com.example.tommyred.pathfindergame.domain.common.utilities.Coordinate
import com.example.tommyred.pathfindergame.mock.Mock
import org.junit.Test

/**
 * Created by Rechtig on 07.06.2017.
 */
class PathFinderTest {

  val testedMap: List<List<GameField>> = Mock.getMockGameBoard()

  val markedMap: List<List<Int>> = Mock.getDefaultMarkedMap()

  val playerPos = Mock.playerPos

  val enemyPos = Mock.enemyPos

  @Test
  fun testDefaultMark() {

    val testMarkedMap: List<List<Int>> = PathFinder.createDefaultMarkedMap(testedMap).map { it.toList() }

    val result: List<List<Int>> = listOf(
            listOf(100, 100, 100),
            listOf(100, 100, 100),
            listOf(100, 100, 100)
    )

    var flag: Boolean = false

    testMarkedMap.forEachIndexed { y, list -> list.forEachIndexed { x, field -> flag = if (field == result[y][x]) flag else true } }

    assert(flag)

  }

  fun testObstacleMark() {

    val testedMap: List<List<Int>> = listOf(
            listOf(100, 100, 100),
            listOf(100, 100, 100),
            listOf(100, 100, 100)
    )

    val resultMap: List<List<Int>> = listOf(
            listOf(0, 1, 2),
            listOf(1, 2, 3),
            listOf(2, 3, 4)
    )

    val startPoint = Coordinate(0, 0)
    val endPoint = Coordinate(2, 2)




  }
}