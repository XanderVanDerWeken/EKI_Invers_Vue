package de.fherfurt.invers.controller

import de.fherfurt.invers.core.Direction
import de.fherfurt.invers.core.Piece
import de.fherfurt.invers.model.ComPlayer
import de.fherfurt.invers.model.MoveInstruction
import de.fherfurt.invers.model.Player
import de.fherfurt.invers.model.UserPlayer
import de.fherfurt.invers.view.Board

/**
 * Controller representing the Game.
 * It is holding on the board and the players, and representing the Game Loop
 *
 * @author Xander Van der Weken
 */
object Game {
    private val player1: Player
    private val player2: Player
    var activePlayer: Player
    var opponentPlayer: Player
    private var board: Board
    var currentPlayer: Int
        private set

    init {
        this.player1 = UserPlayer( Piece.RED, Piece.RED_DOT )
        this.player2 = ComPlayer( Piece.YELLOW, Piece.YELLOW_DOT )
        this.activePlayer = this.player1
        this.opponentPlayer = this.player2
        this.board = Board()
        this.currentPlayer = 1
    }

    /**
     * Returns the score of Player 1
     */
    val scorePlayer1
        get() = board.getPlayerScore( player1 )

    /**
     * Returns the score of Player 2
     */
    val scorePlayer2
        get() = board.getPlayerScore( player2 )

    val piecesInHandPlayer1
        get() = 19 - board.countPlayerPieces(player1)
    val piecesInHandPlayer2
        get() = 19 - board.countPlayerPieces(player2)

    val kindPlayer1
        get() = player1.playerKind

    val kindPlayer2
        get() = player2.playerKind

    val colorPlayer1
        get() = player1.playerColor

    val colorPlayer2
        get() = player2.playerColor

    /**
     * Returns the dottedPiece of the Opponent
     */
    private val opponentDottedPiece
        get() =
            if( activePlayer.dottedPiece === Piece.RED_DOT ) Piece.YELLOW_DOT
            else Piece.RED_DOT


    /**
     * Method representing the Game Loop
     */
    suspend fun playGame() {
        while(!isGameOver()) {
            // Let Player make Move
            if(board.countPlayerPieces( activePlayer ) <= 18) {
                val newMove = activePlayer.makeMove()
                board.applyMove(newMove)
            }

            // Switch Player
            switchPlayer()

            Thread.sleep(1000)
        }
    }

    /**
     * Method to switch Players on active Player
     */
    private fun switchPlayer() {
        if(activePlayer.piece == player1.piece) {
            currentPlayer++
            activePlayer = player2
            opponentPlayer = player1
        }
        else {
            currentPlayer--
            activePlayer = player1
            opponentPlayer = player2
        }
    }

    /**
     * Method to check if Game is Over.
     * Game is over, if one player turned over all his 18 pieces
     *
     * @return true if game is over, else false
     */
    private fun isGameOver() : Boolean {
        return this.board.isGameOver()
    }

    /**
     * Method to get all legal moves
     *
     * @return Map of the directions and List of legal indexes
     */
    fun getLegalMoves() : Map<Direction, List<Int>> {
        return this.board.getAllLegal(opponentDottedPiece)
    }

    /**
     * Method to check if a move is legal
     *
     * @param move move to check for
     * @return true if legal, else false
     */
    fun isLegalMove( move: MoveInstruction ) : Boolean {
        return this.board.isLegal(move.index, move.direction, opponentDottedPiece)
    }

    /**
     * Method to reset the board
     */
    fun resetBoard() {
        this.board = Board()
    }

    /**
     * Method to return the List of pieces
     *
     * @return list of pieces
     */
    fun board() : MutableList<Piece> {
        return this.board.pieces
    }
}