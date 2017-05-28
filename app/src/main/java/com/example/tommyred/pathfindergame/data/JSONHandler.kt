package com.example.tommyred.pathfindergame.data

import android.content.Context
import com.example.tommyred.pathfindergame.domain.board.GameField
import com.example.tommyred.pathfindergame.domain.board.GameFieldType
import com.example.tommyred.pathfindergame.domain.maze.Maze
import java.io.IOException
import java.io.InputStream
import org.json.JSONException
import org.json.JSONObject
import org.json.JSONArray


/**
 * Created by Rechtig on 23.04.2017.
 */

object JSONHandler: Reader {


    /**
     * Get parsed gameboard from JSON
     * @return GameBoard
     */
//    override fun getBoard(context: Context): Maze = try {
//        mapToList(JSONObject(getJSONString(context)).getJSONArray("maze")).toList()
//    } catch (e: JSONException) {
//        throw e
//    }
    override fun getBoard(context: Context): Maze = TODO("Not Implemented")

    /**
     * Read from JSON file and
     * @return String representation of JSON file
     */
    fun getJSONString(context: Context): String = try {
        val stream: InputStream = context.assets.open("maze.json")
        val buffer = ByteArray(stream.available())

        stream.read(buffer)
        stream.close()

        String(buffer)
    } catch (e: IOException) {
        throw e
    }

    /**
     * Map JSON String to GameBoard List
     * @return GameBoard list
     */
    private fun mapToList(x: JSONArray): MutableList<List<GameField>> = (0..x.length() - 1)
            .map {
                (0..x.getJSONArray(it).length() - 1)
                        .map {
                            x.getJSONArray(it).getString(it)
                        }
                        .mapTo(mutableListOf()) { resolveGameField(it) }.toList()
            }
            .mapTo(mutableListOf()) { it }

    fun x(x: JSONArray): MutableList<MutableList<GameField>> = (0..x.length() - 1).map {
        val row = x.getJSONArray(it)

        (0..row.length() - 1).map {
            row.getString(it)
        }.mapTo(mutableListOf()) { resolveGameField(it) }
    }.mapTo(mutableListOf()) { it }


    /**
     * Resolve which GameField use for each String
     * @return GameField
     */
    private fun resolveGameField(s: String): GameField = when (s) {
        "PLAYER" -> GameField(GameFieldType.PLAYER)
        "OBSTACLE" -> GameField(GameFieldType.OBSTACLE)
        "ENEMY" -> GameField(GameFieldType.ENEMY)
        else -> GameField(GameFieldType.BLANK)
    }


}
