package de.fherfurt.invers.core

/**
 * Simple Enum class for a Direction
 *
 * @author Xander Van der Weken
 */
enum class Direction (val nextIndex: Int) {
    UP      (-10),
    DOWN    (10),
    LEFT    (-1),
    RIGHT   (1)
}