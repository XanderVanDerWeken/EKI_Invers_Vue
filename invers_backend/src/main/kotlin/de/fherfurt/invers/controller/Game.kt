package de.fherfurt.invers.controller

import de.fherfurt.invers.core.Direction
import de.fherfurt.invers.core.Piece
import de.fherfurt.invers.model.Move
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
    private var board: Board
    var currentPlayer: Int
        private set

    init {
        this.player1 = UserPlayer( Piece.RED, Piece.RED_DOT )
        this.player2 = UserPlayer( Piece.YELLOW, Piece.YELLOW_DOT )
        this.activePlayer = this.player1
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
            val newMove = activePlayer.makeMove()
            processMove(newMove)

            // Switch Player
            switchPlayer()
        }
    }

    private fun processMove(move: Move) {
        val nextPiece = activePlayer.dottedPiece
        board.applyMove( nextPiece, move.index, move.direction )
    }

    /**
     * Method to switch Players on active Player
     */
    private fun switchPlayer() {
        activePlayer = if(activePlayer.piece == player1.piece) {
            player2
        }
        else {
            player1
        }
    }

    /**
     * Method to check if Game is Over.
     * Game is over, if one player turned over all his 18 pieces
     *
     * @return true if game is over, else false
     */
    private fun isGameOver() : Boolean {
        return scorePlayer1 == 18 || scorePlayer2 == 18
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
     * @param index index to look for
     * @param direction direction to look for
     * @return true if legal, else false
     */
    fun isLegalMove( index: Int, direction: Direction) : Boolean {
        return this.board.isLegal(index, direction, opponentDottedPiece)
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