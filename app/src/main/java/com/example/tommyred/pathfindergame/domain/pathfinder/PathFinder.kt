package com.example.tommyred.pathfindergame.domain.pathfinder

import com.example.tommyred.pathfindergame.domain.board.GameBoard
import com.example.tommyred.pathfindergame.domain.board.GameFieldType
import com.example.tommyred.pathfindergame.domain.utilities.Coordinate

/**
 * Created by Rechtig on 06.06.2017.
 */
object PathFinder {

  private val startValue = 0
  private val defaultValue = 100

  fun findPath(board: GameBoard, startPoint: Coordinate, endPoint: Coordinate): List<Coordinate> {

    var markedBoard: List<List<Int>> = createMarkedMap(board.giveBoard()).map { it.toList() }.toList()

    markedBoard = markedBoard.mapIndexed { y, row -> row.mapIndexed { x, i -> if (board.giveBoard()[y][x].type == GameFieldType.OBSTACLE) 1000 else i } }

    fun markField(coordinate: Coordinate, prevValue: Int): Unit {
      markedBoard = markedBoard.mapIndexed { y, list -> list.mapIndexed { x, i -> if (coordinate.x == x && coordinate.y == y) prevValue else i } }
    }

    fun resolve(coordinate: Coordinate, value: Int) {

      if (checkBounds(coordinate, markedBoard)) return

      // If place is marked and is not default
      if (markedBoard[coordinate.y][coordinate.x] != defaultValue && markedBoard[coordinate.y][coordinate.x] != startValue) return

      // If no fields remain to mark
      if (markedBoard.filter { it.contains(defaultValue) }.isEmpty()) return

      if (markedBoard[coordinate.y][coordinate.x] != 0) {
        val minNeighbor: Int = getSmallestNeighbor(markedBoard, startPoint).second
        markField(coordinate, minNeighbor + 1)
      }

      println("CREATERECCURSION")

      resolve(Coordinate(coordinate.x + 1, coordinate.y), value + 1)
      resolve(Coordinate(coordinate.x - 1, coordinate.y), value + 1)
      resolve(Coordinate(coordinate.x, coordinate.y + 1), value + 1)
      resolve(Coordinate(coordinate.x, coordinate.y - 1), value + 1)
    }

    markField(startPoint, 0)
    resolve(startPoint, 0)

    markedBoard.forEach { row ->
      run {
        row.forEach { print(it) }
        println()
      }
    }

//        var flag = true
    var path: List<Coordinate> = listOf(endPoint)

    while (path.first() != startPoint) {
      path = listOf(getSmallestNeighbor(markedBoard, path.first()).first) + path
    }

    return path
  }


  /**
   * Creates clone of provided map with fields marked as defaultValue
   * @param shadowMap Any map which
   */
  fun <T> createMarkedMap(shadowMap: Collection<Collection<T>>): Collection<Collection<Int>> =
          shadowMap.map { row -> row.map { defaultValue } }

  /**
   * Check if coordinateis in bounds of field
   * @param coordinate represent coordinate to check
   * @param board board in which is coordinate checked
   */
  private fun checkBounds(coordinate: Coordinate, list: List<List<Int>>): Boolean =
          coordinate.x in 0..list.size - 1 && coordinate.y in 0..list[0].size - 1

  private fun getSmallestNeighbor(list: List<List<Int>>, startPoint: Coordinate): Pair<Coordinate, Int> {

    val topCoordinate: Coordinate = Coordinate(startPoint.x, startPoint.y)
    val rightCoordinate: Coordinate = Coordinate(startPoint.x, startPoint.y)
    val bottomCoordinate: Coordinate = Coordinate(startPoint.x, startPoint.y)
    val leftCoordinate: Coordinate = Coordinate(startPoint.x, startPoint.y)

    fun getFieldValue(coordinate: Coordinate): Int = if (checkBounds(coordinate, list)) list[coordinate.x][coordinate.y] else 1000

    val map: Map<Coordinate, Int> = mapOf(
            Pair(topCoordinate, getFieldValue(topCoordinate)),
            Pair(rightCoordinate, getFieldValue(rightCoordinate)),
            Pair(bottomCoordinate, getFieldValue(bottomCoordinate)),
            Pair(leftCoordinate, getFieldValue(leftCoordinate))
    )

    return constructPair(map)
  }

  private fun constructPair(map: Map<Coordinate, Int>): Pair<Coordinate, Int> =
          Pair(checkNotNull(map.minBy { it.value }?.key), checkNotNull(map.minBy { it.value }?.value))
}