package com.example.tommyred.pathfindergame.presentation.game

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.Gravity
import android.view.View
import android.widget.GridLayout

import com.example.tommyred.pathfindergame.R
import com.example.tommyred.pathfindergame.domain.game.board.GameField
import com.example.tommyred.pathfindergame.domain.game.board.GameFieldType

/**
 * Created by user on 15.01.2017.
 */

class BoardFieldWidget(context: Context, gameField: GameField, row: Int, column: Int) : View(context) {

    private var paint: Paint? = null
    private var gameField: GameField? = null

    private val PLAYER_IMAGE_SRC: Int = R.drawable.ic_mood_black_24dp
    private val COIN_IMAGE_SRC: Int = R.drawable.ic_attach_money_black_24dp
    private val ENEMY_IMAGE_SRC: Int = R.drawable.ic_android_black_24dp

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

        paint = Paint()

        paint?.color = if (gameField.type === GameFieldType.COIN)
            Color.YELLOW
        else if (gameField.type === GameFieldType.OBSTACLE)
            Color.BLACK
        else
            Color.LTGRAY

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

        canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), paint)

        val bounds : Rect = canvas.clipBounds
        var image: Drawable? = null

        if (gameField?.type != GameFieldType.BLANK){
            if (gameField?.type == GameFieldType.PLAYER)
                image = resources.getDrawable(PLAYER_IMAGE_SRC)
            else if (gameField?.type == GameFieldType.COIN)
                image = resources.getDrawable(COIN_IMAGE_SRC)
            else
                image = resources.getDrawable(ENEMY_IMAGE_SRC)
            image?.bounds = bounds
            image?.draw(canvas)
        }
    }
}
