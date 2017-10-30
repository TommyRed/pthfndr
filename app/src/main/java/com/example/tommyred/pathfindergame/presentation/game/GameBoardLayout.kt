package com.example.tommyred.pathfindergame.presentation.game

import android.content.Context
import android.util.AttributeSet
import android.widget.GridLayout
import com.example.tommyred.pathfindergame.domain.game.board.GameBoard


/**
 * Created by semanticer on 15.01.2017.
 */

class GameBoardLayout : GridLayout {

    //    private OnMoveListener listener;

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {}

    fun setBoard(gameBoard: GameBoard) {
        removeAllViews()
        rowCount = gameBoard.giveBoard().size
        columnCount = gameBoard.giveBoard().size

        for (r in 0..gameBoard.giveBoard().size - 1) {
            for (c in 0..gameBoard.giveBoard().size - 1) {
                val field = BoardFieldWidget(context, gameBoard.giveBoard()[r][c], r, c)
                addView(field)
            }
        }
    }
}
