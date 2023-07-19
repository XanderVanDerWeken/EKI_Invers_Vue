package de.fherfurt.invers.view

import de.fherfurt.invers.core.Piece
import de.fherfurt.invers.model.Move
import de.fherfurt.invers.model.Player

class ComplayerBoard(pieces: MutableList<Piece>) : Board(pieces) {

    fun executeMove(move: Move, ownPieces: Pair<Piece, Piece>) {
        if( countPieces(ownPieces.first, ownPieces.second) > 18 ) {
            applyMove( move )
        }
    }

    fun evaluateBoard(posPlayerPieces: Pair<Piece, Piece>, negPlayerPieces: Pair<Piece, Piece>) : Int {
        var normalPieces: Int = 0
        var dottedPieces: Int = 0

        for( piece in pieces ) {
            when(piece) {
                posPlayerPieces.first -> normalPieces += 1
                posPlayerPieces.second -> dottedPieces += 5
                negPlayerPieces.first -> normalPieces -= 1
                negPlayerPieces.second -> normalPieces -= 5
                else -> {}
            }
        }

        return dottedPieces - normalPieces
    }
}
