package de.fherfurt.invers.view

import de.fherfurt.invers.core.Piece
import de.fherfurt.invers.model.Move

class SimulationBoard (
    private val config: SimulationConfig,
    private val pieces: MutableList<Piece>,
) {
    private var amountPiecesOne: Int        // Pieces on Board of Player 1
    private var amountPiecesTwo: Int        // Pieces on Board of Player 2

    init {
        amountPiecesOne = Board.countPlayerPieces( pieces, config.player1 )
        amountPiecesTwo = Board.countPlayerPieces( pieces, config.player2 )
    }

    /**
     * Method to get SimulationBoards for all possible Moves
     *
     * @return List of Simulation Boards
     */
    // TODO: Fix Player Seperation
    fun getPossibleMoves() : List<SimulationBoard> {
        // Definition of Simulation State
        // TODO: Fix get dot piece
        val ownDottedPiece = config.player1.dottedPiece
        val oppDottedPiece = config.player2.dottedPiece

        // Getting all Legal Moves
        val allLegalMoves : List<Move> = Board.getAllLegal(
            pieces = pieces,
            opponentDottedPiece = oppDottedPiece
        ).flatMap { entry ->
            entry.value.map { value ->
                Move(entry.key, value, ownDottedPiece)
            }
        }

        // Building all possible SimulationBoards
        val simulationBoards: List<SimulationBoard> = allLegalMoves.map { move ->
            if (amountPiecesOne > 0) {
                val newPieces = Board.applyMoveOnCopy( pieces, move )
                SimulationBoard(
                    config = config,
                    pieces = newPieces
                )
            }
            else {
                SimulationBoard(
                    config = config,
                    pieces = pieces
                )
            }
        }

        // Returning Result
        return simulationBoards
    }

    fun evaluateBoard() : Int {
        return -1
    }
}