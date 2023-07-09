package de.fherfurt.invers.model

import de.fherfurt.invers.controller.Game
import de.fherfurt.invers.core.Piece
import de.fherfurt.invers.view.Board
import de.fherfurt.invers.view.SimulationBoard

/**
 * Player using an AI to play
 *
 * @see Player
 * @author Xander Van der Weken
 */
class ComPlayer(piece: Piece, dottedPiece: Piece) : Player(piece, dottedPiece, aiPlayerKind) {

    private val opponentDottedPiece: Piece
        get() =
            if(dottedPiece == Piece.RED_DOT) Piece.YELLOW_DOT
            else Piece.YELLOW_DOT

    companion object {
        const val aiPlayerKind = "AI Player"
        const val maxDepth = 3
    }

    /**
     * Method to make a move.
     * It is waiting while no move is set through the API.
     * It is calculating the move with a min max algorithm
     *
     * @return move to be made and applied on the Board
     */
    override fun makeMove() : Move {
        val simulationBoard = SimulationBoard( Game.board(), dottedPiece, opponentDottedPiece )

        return simulationBoard.getBestMove()
    }
}