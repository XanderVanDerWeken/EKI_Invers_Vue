package de.fherfurt.invers.model

import de.fherfurt.invers.core.Piece

/**
 * Abstract class representing a Player, with all basics for a playing.
 *
 * @author Xander Van der Weken
 */
abstract class Player protected constructor(
    val piece: Piece,
    val dottedPiece: Piece,
) {

    /**
     * Abstract Method to make a Move
     *
     * @return move to be made and applied on the Board
     */
    abstract fun makeMove() : Move
}