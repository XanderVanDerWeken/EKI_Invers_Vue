package de.fherfurt.invers.model

import de.fherfurt.invers.controller.Game
import de.fherfurt.invers.core.Piece

/**
 * Human Player Implementation, to use interaction from the API to play.
 *
 * @see Player
 * @author Xander Van der Weken
 */
class UserPlayer(piece: Piece, dottedPiece: Piece) : Player(piece, dottedPiece, userPayerKind){

    companion object {
        const val userPayerKind = "User Player"
    }

    private var nextMove: MoveInstruction? = null

    private val lock = Any()

    /**
     * Method to make a move.
     * It is waiting while no move is set through the API.
     *
     * @return move to be made and applied on the Board
     */
    override fun makeMove() : Move {
        updateMove( null )
        while ( readMove() == null ) { }

        val moveIns = MoveInstruction(
            readMove()!!.direction,
            readMove()!!.index
        )

        return Move(
            moveIns,
            dottedPiece
        )
    }

    /**
     * Method to write the nextMove Variable synchronized.
     * Realising that the nextMove Attribute is thread-safe
     *
     * @param move move to set
     */
    fun updateMove( move: MoveInstruction? ) {
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
    fun readMove() : MoveInstruction? {
        synchronized( lock ) {
            return nextMove
        }
    }
}