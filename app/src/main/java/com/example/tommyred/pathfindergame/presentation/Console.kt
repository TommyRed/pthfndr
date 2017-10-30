package com.example.tommyred.pathfindergame.presentation

/**
 * Created by Rechtig on 07.06.2017.
 */
object Console {

  fun <T> print(value: T) = println(value)

  fun printListInt(list: List<List<Int>>) = list.forEach { row ->
    run {
      row.forEach { print(it) }
      println()
    }
  }
}