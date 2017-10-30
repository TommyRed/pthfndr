package com.example.tommyred.pathfindergame.domain.pathfinder

import com.example.tommyred.pathfindergame.domain.game.board.GameBoard
import com.example.tommyred.pathfindergame.domain.game.board.GameFieldType
import com.example.tommyred.pathfindergame.domain.common.utilities.Coordinate

/**
 * Created by Rechtig on 06.06.2017.
 */
object PathFinder {

  private val startValue = 0
  private val defaultValue = 100

  fun findPath(board: GameBoard, startPoint: Coordinate, endPoint: Coordinate): List<Coordinate> {

    var markedBoard: List<List<Int>> = createDefaultMarkedMap(board.giveBoard()).map { it.toList() }

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

      resolve(Coordinate(coordinate.x + 1, coordinate.y), value + 1)
      resolve(Coordinate(coordinate.x - 1, coordinate.y), value + 1)
      resolve(Coordinate(coordinate.x, coordinate.y + 1), value + 1)
      resolve(Coordinate(coordinate.x, coordinate.y - 1), value + 1)
    }

    markField(startPoint, 0)
    resolve(startPoint, 0)


//        var flag = true
    var path: List<Coordinate> = listOf(getSmallestNeighbor(markedBoard, endPoint).first, endPoint)
    var i: Int = 1

    path.forEach { println("$ it = $it") }

    println(endPoint)
    println(startPoint)

    while (path.first() != endPoint) {
      println("#    $startPoint === ${path.first()}")
      path = listOf(getSmallestNeighbor(markedBoard, path.first()).first) + path
      i++
    }

    return listOf(startPoint)
  }


  /**
   * Creates clone of provided map with fields marked as defaultValue
   * @param shadowMap Any map which
   */
  fun <T> createDefaultMarkedMap(shadowMap: Collection<Collection<T>>): Collection<Collection<Int>> =
          shadowMap.map { row -> row.map { defaultValue } }

  /**
   * Mark obstacles to marked map depending on shadow's map value
   * @param shadowMap Any map containing bannedValue specified below
   * @param bannedValue Banned value which represents
   */
  fun <T> markObstacles(shadowMap: List<List<T>>, map: List<List<Int>>, bannedValue: T, substatial: Int): List<List<Int>> =
          map.mapIndexed { y, row -> row.mapIndexed { x, field -> if (shadowMap[y][x] == bannedValue) substatial else field } }

  /**
   * Check if coordinateis in bounds of field
   * @param coordinate represent coordinate to check
   * @param list board in which is coordinate checked
   */
  private fun checkBounds(coordinate: Coordinate, list: List<List<Int>>): Boolean =
          coordinate.x in 0..list.size - 1 && coordinate.y in 0..list[0].size - 1


  private fun getSmallestNeighbor(list: List<List<Int>>, startPoint: Coordinate): Pair<Coordinate, Int> {
    val map: Map<Coordinate, Int> = getNeighbours(startPoint, list)

    val x = Pair(checkNotNull(map.minBy { it.value }?.key), checkNotNull(map.minBy { it.value }?.value)).first

    println("#    SMALLEST NEIGHBOR : $x")

    return Pair(checkNotNull(map.minBy { it.value }?.key), checkNotNull(map.minBy { it.value }?.value))
  }

  private fun getNeighbours(startPoint: Coordinate, list: List<List<Int>>): Map<Coordinate, Int> {
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
    return map
  }
}