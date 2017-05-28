package com.example.tommyred.pathfindergame.domain.entities

import com.example.tommyred.pathfindergame.domain.board.GameBoard
import com.example.tommyred.pathfindergame.domain.board.GameFieldType
import com.example.tommyred.pathfindergame.domain.utilities.Coordinate
import com.example.tommyred.pathfindergame.domain.utilities.Direction
import com.example.tommyred.pathfindergame.domain.utilities.state.Error
import com.example.tommyred.pathfindergame.domain.utilities.state.Success
import com.example.tommyred.pathfindergame.mock.Mock
import com.example.tommyred.pathfindergame.presentation.game.GamePresenter
import android.system.Os.poll
import java.util.*
import kotlin.collections.HashSet


/**
 * Created by Rechtig on 25.05.2017.
 */
class Enemy(var coordinate: Coordinate, val type: GameFieldType) : Entity {


    fun makeMove(gameboard: GameBoard, destination: Coordinate): Unit {

        println("Enemy on location x: ${coordinate.x}, y: ${coordinate.y}")

        coordinate = generateTurn(gameboard, destination)
    }

    private fun generateTurn(board: GameBoard, destination: Coordinate): Coordinate {

        val defaultValue: Int = 100
        val startValue: Int = 0
        val startPoint: Coordinate = coordinate

        var kok: List<List<Int>> = (0..board.giveBoard().size - 1).map { (0..board.giveBoard().size - 1).map { defaultValue }.toList() }.toList()

        kok = kok.mapIndexed { y, row -> row.mapIndexed { x, i -> if (board.giveBoard()[y][x].type == GameFieldType.OBSTACLE) 1000 else i } }

        fun markField(coordinate: Coordinate, prevValue: Int): Unit {
            kok = kok.mapIndexed { y, list -> list.mapIndexed { x, i -> if (coordinate.x == x && coordinate.y == y) prevValue else i } }
        }

        fun resolve(coordinate: Coordinate, value: Int) {

            if (coordinate.x < 0) return
            if (coordinate.y < 0) return
            if (coordinate.x >= kok.size) return
            if (coordinate.y >= kok.size) return

            // If place is marked and is not default
            if (kok[coordinate.y][coordinate.x] != defaultValue && kok[coordinate.y][coordinate.x] != startValue) return

            // If no fields remain to mark
            if (kok.filter { it.contains(defaultValue) }.isEmpty()) return


            if (kok[coordinate.y][coordinate.x] != 0) {
                val top: Int =
                        if (inBounds(Coordinate(coordinate.x, coordinate.y - 1), kok.size))
                            kok[coordinate.y - 1][coordinate.x]
                        else
                            1000

                val right: Int =
                        if (inBounds(Coordinate(coordinate.x + 1, coordinate.y), kok.size))
                            kok[coordinate.y][coordinate.x + 1]
                        else
                            1000

                val bottom: Int =
                        if (inBounds(Coordinate(coordinate.x, coordinate.y + 1), kok.size))
                            kok[coordinate.y + 1][coordinate.x]
                        else
                            1000

                val left: Int =
                        if (inBounds(Coordinate(coordinate.x - 1, coordinate.y), kok.size))
                            kok[coordinate.y][coordinate.x - 1]
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

        markField(coordinate, 0)
        resolve(startPoint, 0)

        fun generatePath(coord: Coordinate): Coordinate {
            val top: Int =
                    if (inBounds(Coordinate(coord.x, coord.y - 1), kok.size))
                        kok[coord.y - 1][coord.x]
                    else
                        1000

            val right: Int =
                    if (inBounds(Coordinate(coord.x + 1, coord.y), kok.size))
                        kok[coord.y][coord.x + 1]
                    else
                        1000

            val bottom: Int =
                    if (inBounds(Coordinate(coord.x, coord.y + 1), kok.size))
                        kok[coord.y + 1][coord.x]
                    else
                        1000

            val left: Int =
                    if (inBounds(Coordinate(coord.x - 1, coord.y), kok.size))
                        kok[coord.y][coord.x - 1]
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

        while (path.first() != coordinate) {

            path = listOf(generatePath(path.first())) + path

//            println("PATH last : ${path.first()}, destination: ${destination}")

        }

        return path[1]
    }

    fun inBounds(coordinate: Coordinate, bound: Int): Boolean = coordinate.x in 0..(bound - 1) && coordinate.y in 0..(bound - 1)

    /**
     * Returns location of the enemy
     */
    override fun getLocation(): Coordinate = coordinate

    override fun getFieldType(): GameFieldType = type
}