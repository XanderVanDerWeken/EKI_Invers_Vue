package de.fherfurt.invers.model

import de.fherfurt.invers.core.Direction
import de.fherfurt.invers.core.Piece

/**
 * Data Class representing a Move.
 * A move is represented by a direction and index
 *
 * @author Xander Van der Weken
 */
data class Move(
    val direction: Direction,
    val index: Int,
    val piece: Piece
)
