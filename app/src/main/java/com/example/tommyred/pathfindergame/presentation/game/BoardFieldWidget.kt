package com.example.tommyred.pathfindergame.presentation.game

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.text.TextPaint
import android.view.Gravity
import android.view.View
import android.widget.GridLayout

import com.example.tommyred.pathfindergame.R
import com.example.tommyred.pathfindergame.domain.board.GameField
import com.example.tommyred.pathfindergame.domain.board.GameFieldType

/**
 * Created by user on 15.01.2017.
 */

class BoardFieldWidget(context: Context, gameField: GameField, row: Int, column: Int) : View(context) {

    private val textPaint: TextPaint? = null
    private val circlePaint: Paint? = null
    private var gridPaint: Paint? = null
    private var gameField: GameField? = null

    var row: Int = 0
        private set

    var column: Int = 0
        private set

    private var marginSize: Float = 0.toFloat()

    init {
        init(gameField, row, column)
    }

    private fun init(gameField: GameField, row: Int, column: Int) {
        this.gameField = gameField
        this.row = row
        this.column = column
        isClickable = true

        gridPaint = Paint()

        gridPaint!!.color = if (gameField.type === GameFieldType.BLANK)
            Color.LTGRAY
        else if (gameField.type === GameFieldType.ENEMY)
            Color.RED
        else if (gameField.type === GameFieldType.OBSTACLE)
            Color.BLACK
        else if (gameField.type === GameFieldType.PLAYER)
            Color.BLUE
        else
            Color.YELLOW

        val attrs = intArrayOf(R.attr.selectableItemBackground)
        val typedArray = context.obtainStyledAttributes(attrs)
        val backgroundResource = typedArray.getResourceId(0, 0)
        setBackgroundResource(backgroundResource)
        typedArray.recycle()

        val fieldSize = resources.getDimension(R.dimen.field_size)
        marginSize = resources.getDimension(R.dimen.margin_size)
        val param = GridLayout.LayoutParams()
        param.height = fieldSize.toInt()
        param.width = fieldSize.toInt()
        param.rightMargin = 4
        param.topMargin = 4
        param.setGravity(Gravity.CENTER)
        param.rowSpec = GridLayout.spec(row)
        param.columnSpec = GridLayout.spec(column)
        layoutParams = param

    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val width = width
        val height = height

        canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), gridPaint!!)
    }
}
