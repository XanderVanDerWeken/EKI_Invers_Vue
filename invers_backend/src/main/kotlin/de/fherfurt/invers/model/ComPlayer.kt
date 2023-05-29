package de.fherfurt.invers.model

import de.fherfurt.invers.core.Piece

/**
 * Player using an AI to play
 *
 * @see Player
 * @author Xander Van der Weken
 */
class ComPlayer(piece: Piece, dottedPiece: Piece) : Player(piece, dottedPiece) {
    override fun makeMove() : Move {
        TODO("Not yet implemented")
    }
}