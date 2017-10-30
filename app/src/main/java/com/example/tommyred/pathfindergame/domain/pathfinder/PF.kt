package com.example.tommyred.pathfindergame.domain.pathfinder

import com.example.tommyred.pathfindergame.domain.common.utilities.Coordinate
import com.example.tommyred.pathfindergame.domain.game.board.GameBoard
import com.example.tommyred.pathfindergame.domain.game.board.GameFieldType

/**
 * Created by Rechtig on 12.06.2017.
 */
object PF {

  /**
   * Generate marked map
   */
  fun <T> genMarkedMap(shadowMap: List<List<T>>, default: Int): List<List<Int>> = genDefaultMap(shadowMap, default)

  /**
   * Generate map marked by default value
   */
  fun <T> genDefaultMap(shadowMap: List<List<T>>, default: Int): List<List<Int>> = shadowMap.map { row -> row.map { default } }

  /**
   * Mark obstacles
   */
  fun <T> markObstacles(map: List<List<Int>>, shadowMap: List<List<T>>, obstacle: T, value: Int): List<List<Int>> = map.mapIndexed { y, row -> row.mapIndexed { x, field -> if (shadowMap[y][x] == obstacle) value else field } }


  fun <T> print(list: List<List<T>>) = list.forEach { it ->
    kotlin.run {
      it.forEach { print(" $it") }
      println()
    }
  }





  /**
   * Generate next available move
   */
  fun <T> getNextMove(list: List<List<T>>, start: Coordinate, end: Coordinate): Coordinate = generatePath(list, start, end)[0]

  /**
   * Generate path
   */
  fun <T> generatePath(board: List<List<T>>, start: Coordinate, end: Coordinate): List<Coordinate> {

    var markedMap: List<List<Int>> = genMarkedMap(board, board.size)

    markedMap = markObstacles(markedMap, board, GameFieldType.OBSTACLE, board.size * 2)

    print( markObstacles(markedMap, board, GameFieldType.OBSTACLE, board.size * 2))

    return listOf(Coordinate(1, 9))

  }





  fun generateTurn(board: GameBoard, destination: Coordinate, start: Coordinate): Coordinate {

    val defaultValue: Int = 100
    val startValue: Int = 0

    var mappedBoard: List<List<Int>> = (0..board.giveBoard().size - 1).map { (0..board.giveBoard().size - 1).map { defaultValue }.toList() }.toList()

    mappedBoard = mappedBoard.mapIndexed { y, row -> row.mapIndexed { x, i -> if (board.giveBoard()[y][x].type == GameFieldType.OBSTACLE) 1000 else i } }

    fun markField(coordinate: Coordinate, prevValue: Int): Unit {
      mappedBoard = mappedBoard.mapIndexed { y, list -> list.mapIndexed { x, i -> if (coordinate.x == x && coordinate.y == y) prevValue else i } }
    }

    fun resolve(coordinate: Coordinate, value: Int) {

      if (coordinate.x < 0) return
      if (coordinate.y < 0) return
      if (coordinate.x >= mappedBoard.size) return
      if (coordinate.y >= mappedBoard.size) return

      // If place is marked and is not default
      if (mappedBoard[coordinate.y][coordinate.x] != defaultValue && mappedBoard[coordinate.y][coordinate.x] != startValue) return

      // If no fields remain to mark
      if (mappedBoard.filter { it.contains(defaultValue) }.isEmpty()) return

      if (mappedBoard[coordinate.y][coordinate.x] != 0) {
        val top: Int =
                if (inBounds(Coordinate(coordinate.x, coordinate.y - 1), mappedBoard.size))
                  mappedBoard[coordinate.y - 1][coordinate.x]
                else
                  1000

        val right: Int =
                if (inBounds(Coordinate(coordinate.x + 1, coordinate.y), mappedBoard.size))
                  mappedBoard[coordinate.y][coordinate.x + 1]
                else
                  1000

        val bottom: Int =
                if (inBounds(Coordinate(coordinate.x, coordinate.y + 1), mappedBoard.size))
                  mappedBoard[coordinate.y + 1][coordinate.x]
                else
                  1000

        val left: Int =
                if (inBounds(Coordinate(coordinate.x - 1, coordinate.y), mappedBoard.size))
                  mappedBoard[coordinate.y][coordinate.x - 1]
                else
                  1000

        val minNeighbor: Int = checkNotNull(listOf<Int>(top, right, bottom, left).sorted().min())

        markField(coordinate, minNeighbor + 1)
      }

      resolve(Coordinate(coordinate.x + 1, coordinate.y), value + 1)
      resolve(Coordinate(coordinate.x - 1, coordinate.y), value + 1)
      resolve(Coordinate(coordinate.x, coordinate.y + 1), value + 1)
      resolve(Coordinate(coordinate.x, coordinate.y - 1), value + 1)
    }

    markField(start, 0)
    resolve(start, 0)

    fun generatePath(coord: Coordinate): Coordinate {
      val top: Int =
              if (inBounds(Coordinate(coord.x, coord.y - 1), mappedBoard.size))
                mappedBoard[coord.y - 1][coord.x]
              else
                1000

      val right: Int =
              if (inBounds(Coordinate(coord.x + 1, coord.y), mappedBoard.size))
                mappedBoard[coord.y][coord.x + 1]
              else
                1000

      val bottom: Int =
              if (inBounds(Coordinate(coord.x, coord.y + 1), mappedBoard.size))
                mappedBoard[coord.y + 1][coord.x]
              else
                1000

      val left: Int =
              if (inBounds(Coordinate(coord.x - 1, coord.y), mappedBoard.size))
                mappedBoard[coord.y][coord.x - 1]
              else
                1000

      val map: Map<Coordinate, Int> = mapOf(
              Pair(Coordinate(coord.x, coord.y - 1), top),
              Pair(Coordinate(coord.x + 1, coord.y), right),
              Pair(Coordinate(coord.x, coord.y + 1), bottom),
              Pair(Coordinate(coord.x - 1, coord.y), left)
      )

      val minNeighbor: Int = checkNotNull(map.values.sorted().min())

      val neighbor: Coordinate = map.filter { it.value == minNeighbor }.keys.first()

      return neighbor
    }


//        var flag = true
    var path: List<Coordinate> = listOf(destination)

    while (path.first() != start) {
      path = listOf(generatePath(path.first())) + path

//            print("=> ${path.first()}")
    }

    return path[path.size - 2]
  }


  fun inBounds(coordinate: Coordinate, bound: Int): Boolean = coordinate.x in 0..(bound - 1) && coordinate.y in 0..(bound - 1)

}