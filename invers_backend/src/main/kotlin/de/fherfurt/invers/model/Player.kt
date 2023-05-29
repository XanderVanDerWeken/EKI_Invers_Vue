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

    private var amountPieces = 18
    private var amountDottedPieces = 0

    /**
     * Abstract Method to make a Move
     *
     * @return move to be made and applied on the Board
     */
    abstract fun makeMove() : Move

    /**
     * Method to get amount of normal pieces
     *
     * @return amount of normal pieces
     */
    protected fun amountPieces(): Int {
        return amountPieces
    }

    /**
     * Method to get amount of dotted pieces
     *
     * @return amount of dotted pieces
     */
    protected fun amountDottedPieces(): Int {
        return amountDottedPieces
    }

    /**
     * Method to gain dotted Pieces
     *
     * @param amount amount to gain
     */
    protected fun gainDottedPieces(amount: Int) {
        amountDottedPieces += amount
    }

    /**
     * Method to use dotted pieces
     *
     * @param amount amount of dotted pieces to use
     */
    fun useDottedPiece(amount: Int) {
        amountDottedPieces++
    }

    /**
     * Method to use normal pieces
     *
     * @param amount amount of normal pieces to use
     */
    fun usePieces(amount: Int) {
        amountPieces++
    }
}