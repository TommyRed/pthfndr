package com.example.tommyred.pathfindergame.presentation.game

import android.content.Context
import android.os.Bundle
import com.example.tommyred.pathfindergame.data.GSONReader

import com.example.tommyred.pathfindergame.domain.board.GameBoardImpl
import com.example.tommyred.pathfindergame.domain.Game
import com.example.tommyred.pathfindergame.domain.board.GameBoard
import com.example.tommyred.pathfindergame.domain.board.GameField
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

class GamePresenter : RxPresenter<GameView>() {

    private var game = Game(GameBoardImpl(
            Mock.createMockGameBoard()),
            Player(Coordinate(0, 0), GameFieldType.PLAYER),
            Enemy(Coordinate(2, 2), GameFieldType.ENEMY),
            Coordinate(1, 0)
    )

    override fun onCreate(savedState: Bundle?) {
        super.onCreate(savedState)

        view().subscribe { view -> view.showGameBoard(game.board) }
    }

    fun createGame(context: Context, id: Int) {

        val maze = GSONReader.getBoard(context)

        val x : List<List<GameField>> = maze.maze.map { it.map { field -> GameField(GameFieldType.getField(field)) } }
        val playerPos: Coordinate = Coordinate(maze.playerPos[0], maze.playerPos[1])
        val enemyPos: Coordinate = Coordinate(maze.enemyPos[0], maze.enemyPos[1])
        val coinPos: Coordinate = Coordinate(maze.coinPos[0], maze.coinPos[1])

        println("Player Pos: ${playerPos}, Enemy pos: ${enemyPos}")


        game = Game(GameBoardImpl(x), Player(playerPos, GameFieldType.PLAYER), Enemy(enemyPos, GameFieldType.ENEMY), coinPos)
    }

    /**
     * Function that calls game to make a move
     */
    fun callForMove(direction: Direction) {
        val newGameboard: GameBoard = game.onMoveMade(direction, this)
        view().subscribe { view -> view.showGameBoard(newGameboard) }
    }

    fun result() = view().subscribe { view -> view.result("You have lost") }


    fun showMessage(message: String) = view().subscribe { view -> view.warn(message) }
}
