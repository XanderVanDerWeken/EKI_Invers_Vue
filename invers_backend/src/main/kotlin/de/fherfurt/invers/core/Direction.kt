package de.fherfurt.invers.core

/**
 * Enum class for a Direction.
 * Containing the nextIndex and lastIndex of each direction.
 *
 * @author Xander Van der Weken
 */
enum class Direction (val previousIndex: Int, val nextIndex: Int) {
    UP      (10, -10),
    DOWN    (-10, 10),
    LEFT    (1, -1),
    RIGHT   (-1, 1)
}
