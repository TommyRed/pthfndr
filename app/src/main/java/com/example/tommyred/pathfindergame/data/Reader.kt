package com.example.tommyred.pathfindergame.data

import android.content.Context
import com.example.tommyred.pathfindergame.domain.game.maze.Maze

/**
 * Created by Rechtig on 24.04.2017.
 */
interface Reader {
    fun getBoard(context: Context): Maze
}