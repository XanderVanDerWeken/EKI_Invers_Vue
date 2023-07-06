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
    val playerKind: String
) {
    protected var amountOfPiecesInHand: Int = 1

    val playerColor
        get() = piece.text

    /**
     * Abstract Method to make a Move
     *
     * @return move to be made and applied on the Board
     */
    abstract fun makeMove() : Move

    /**
     * Method to gain a new Piece in hand
     */
    fun gainPiece() {
        this.amountOfPiecesInHand++
    }

    /**
     * Method to use a Piece in hand
     */
    fun usePiece() {
        this.amountOfPiecesInHand--
    }

    /**
     * Method to see if has a Piece in hand
     *
     * @return true if more then 0
     */
    fun hasPiecesInHand() : Boolean {
        return this.amountOfPiecesInHand > 0
    }
}