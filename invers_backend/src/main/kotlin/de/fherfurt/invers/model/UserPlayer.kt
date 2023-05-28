package de.fherfurt.invers.model

import de.fherfurt.invers.core.Piece

class UserPlayer(piece: Piece, dottedPiece: Piece) : Player(piece, dottedPiece){

    private var nextMove: Move? = null

    private val lock = Any()

    override fun makeMove() : Move {
        updateMove( null )
        while ( readMove() == null ) { }

        return readMove()!!
    }

    fun updateMove( move: Move? ) {
        synchronized( lock ) {
            nextMove = move
        }
    }

    fun readMove() : Move? {
        synchronized( lock ) {
            return nextMove
        }
    }
}