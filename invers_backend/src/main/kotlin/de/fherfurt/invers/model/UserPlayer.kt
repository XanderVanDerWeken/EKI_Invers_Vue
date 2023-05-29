package de.fherfurt.invers.model

import de.fherfurt.invers.core.Piece

/**
 * Human Player Implementation, to use interaction from the API to play.
 *
 * @see Player
 * @author Xander Van der Weken
 */
class UserPlayer(piece: Piece, dottedPiece: Piece) : Player(piece, dottedPiece){

    private var nextMove: Move? = null

    private val lock = Any()

    /**
     * Method to make a move.
     * It is waiting while no move is set through the API.
     *
     * @return move t obe made and applied on the Board
     */
    override fun makeMove() : Move {
        updateMove( null )
        while ( readMove() == null ) { }

        return readMove()!!
    }

    /**
     * Method to write the nextMove Variable synchronized.
     * Realising that the nextMove Attribute is thread-safe
     *
     * @param move move to set
     */
    fun updateMove( move: Move? ) {
        synchronized( lock ) {
            nextMove = move
        }
    }

    /**
     * Method to read the nextMove Variable synchronized.
     * Realising that the nextMove Attribute is thread-safe
     *
     * @return Move if move was set, else null
     */
    fun readMove() : Move? {
        synchronized( lock ) {
            return nextMove
        }
    }
}