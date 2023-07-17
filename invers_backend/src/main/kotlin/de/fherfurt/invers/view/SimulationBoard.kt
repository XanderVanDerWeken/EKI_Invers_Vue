package de.fherfurt.invers.view

import de.fherfurt.invers.core.Piece
import de.fherfurt.invers.model.Move
import de.fherfurt.invers.model.Player

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
                this
            }
        }

        // Returning Result
        return simulationBoards
    }

    // TODO: Fix Player Seperation
    fun evaluateBoard( isMaximizing: Boolean ) : Int {
        val (playerOneScore, playerTwoScore) = countPiecesWithWeightForPlayer(
            isMaximizing = isMaximizing,
            ownPlayer = config.player1,
            oppPlayer = config.player2
        )

        val totalScore = playerOneScore - playerTwoScore

        return totalScore
    }

    private fun countPiecesWithWeightForPlayer(isMaximizing: Boolean, ownPlayer: Player, oppPlayer: Player): Pair<Int, Int> {
        var normalPieces: Int = 0
        var dottedPieces: Int = 0

        for( piece in pieces ) {
            when(piece) {
                ownPlayer.piece -> normalPieces += config.weightNormalPiece
                ownPlayer.dottedPiece -> dottedPieces += config.weightDottedPiece
                oppPlayer.piece -> normalPieces -= config.weightNormalPiece
                oppPlayer.dottedPiece -> normalPieces -= config.weightDottedPiece
                else -> 0
            }
        }

        return if(isMaximizing) Pair(normalPieces, dottedPieces) else Pair(dottedPieces, normalPieces)
    }
}