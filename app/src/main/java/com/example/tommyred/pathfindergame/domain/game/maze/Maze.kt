package com.example.tommyred.pathfindergame.domain.maze

import com.google.gson.annotations.SerializedName

/**
 * Created by Rechtig on 28.05.2017.
 */
data class Maze(val maze: List<List<Int>>, val playerPos: Array<Int>, val enemyPos: Array<Int>, val coinPos: Array<Int>)