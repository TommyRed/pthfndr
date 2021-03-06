package com.example.tommyred.pathfindergame.data

import android.content.Context
import com.example.tommyred.pathfindergame.domain.game.maze.Maze
import com.google.gson.Gson
import java.io.IOException
import java.io.InputStream

/**
 * Created by Rechtig on 24.04.2017.
 */
object GSONReader: Reader {

    override fun getBoard(context: Context): Maze = Gson().fromJson(getJSONString(context), Maze::class.java)

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
}