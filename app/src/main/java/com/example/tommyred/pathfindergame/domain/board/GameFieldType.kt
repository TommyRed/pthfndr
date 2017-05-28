package com.example.tommyred.pathfindergame.domain.board

/**
 * Created by Rechtig on 23.04.2017.
 */

enum class GameFieldType {
    PLAYER,
    OBSTACLE,
    ENEMY,
    COIN,
    BLANK;


    companion object{
        fun getField(id: Int): GameFieldType = when(id){
            1 -> PLAYER
            2 -> OBSTACLE
            3 -> ENEMY
            4 -> COIN
            else -> BLANK
        }
    }
}