package de.fherfurt.invers.view

import de.fherfurt.invers.core.Piece
import de.fherfurt.invers.model.Move
import de.fherfurt.invers.model.Player

class ComplayerBoard(pieces: MutableList<Piece>) : Board(pieces) {

    fun executeMove(move: Move, player: Player) {
        if( countPlayerPieces( player ) > 18 ) {
            applyMove( move )
        }
    }

    fun evaluateBoard(posPlayer: Player, negPlayer: Player) : Int {
        var normalPieces: Int = 0
        var dottedPieces: Int = 0

        for( piece in pieces ) {
            when(piece) {
                posPlayer.piece -> normalPieces += 1
                posPlayer.dottedPiece -> dottedPieces += 5
                negPlayer.piece -> normalPieces -= 1
                negPlayer.dottedPiece -> normalPieces -= 5
                else -> {}
            }
        }

        return dottedPieces - normalPieces
    }
}
