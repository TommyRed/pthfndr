package com.example.tommyred.pathfindergame.domain.common.utilities

/**
 * Created by Rechtig on 21.05.2017.
 */
data class Coordinate(val x: Int, val y: Int) {

  override fun equals(other: Any?): Boolean =
          if (other is Coordinate)
            other.x == this.x && other.y == this.y
          else
            false

  override fun toString(): String {
    return "[$x, $y]"
  }

  override fun hashCode(): Int = 31 * x + y
}