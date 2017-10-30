package com.example.tommyred.pathfindergame.domain.utilities.state.errors

import com.example.tommyred.pathfindergame.domain.utilities.state.Error

/**
 * Created by Rechtig on 21.05.2017.
 */
enum class BoardError(val x: String): Error {
    MOVE_ON_WALL("Play on wall"),
    MOVE_ON_ENEMY("Play on Enemy"),
    MOVE_OUT_OF_BOUNDS("Play out of bounds");


    override fun getMessage(): String = x
}