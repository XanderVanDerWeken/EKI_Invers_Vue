package de.fherfurt.invers.view

import de.fherfurt.invers.core.Piece
import de.fherfurt.invers.model.Move

/**
 * Class representing a Board with extensions for Complayer.
 * It is holding the list and performing all operations on the board.
 *
 * @see Board
 * @author Xander Van der Weken
 */
class ComplayerBoard(pieces: MutableList<Piece>) : Board(pieces) {

    /**
     * Method to execute a Move if possible
     *
     * @param move move to execute
     * @param ownPieces ownPieces to check if a move is possible
     */
    fun executeMove(move: Move, ownPieces: Pair<Piece, Piece>) {
        if( countPieces(ownPieces.first, ownPieces.second) > 18 ) {
            applyMove( move )
        }
    }

    /**
     * Method to evaluate the Board
     *
     * @param posPlayerPieces pair of pieces for the positive calculated Player
     * @param negPlayerPieces pair of pieces for the negative calculated Player
     * @return total score on Board
     */
    fun evaluateBoard(posPlayerPieces: Pair<Piece, Piece>, negPlayerPieces: Pair<Piece, Piece>) : Int {
        var normalPieces = 0
        var dottedPieces = 0

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
