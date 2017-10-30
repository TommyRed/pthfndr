package com.example.tommyred.pathfindergame.domain.common.utilities.state

/**
 * Created by Rechtig on 21.05.2017.
 */
interface Error: State {

    /**
     * @return error message
     */
    fun getMessage(): String
}