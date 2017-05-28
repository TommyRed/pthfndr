package com.example.tommyred.pathfindergame.presentation.game

import android.content.Context
import android.os.Bundle

//import com.example.tommyred.pathfindergame.domain.Game;
//import com.example.tommyred.pathfindergame.domain.GameImpl;
//import com.example.tommyred.pathfindergame.domain.GameBoard;

import com.example.tommyred.pathfindergame.domain.board.GameBoardImpl
import com.example.tommyred.pathfindergame.domain.Game
import com.example.tommyred.pathfindergame.domain.board.GameBoard
import com.example.tommyred.pathfindergame.domain.board.GameFieldType
import com.example.tommyred.pathfindergame.domain.entities.Enemy
import com.example.tommyred.pathfindergame.domain.entities.Player
import com.example.tommyred.pathfindergame.domain.utilities.Coordinate
import com.example.tommyred.pathfindergame.domain.utilities.Direction
import com.example.tommyred.pathfindergame.mock.Mock

import nucleus.presenter.RxPresenter

/**
 * Created by Rechtig on 19.03.2017.
 */

class ResultPresenter : RxPresenter<ResultView>() {

    override fun onCreate(savedState: Bundle?) {
        super.onCreate(savedState)


    }
}
