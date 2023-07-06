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
    private var board: Board
    var currentPlayer: Int
        private set

    init {
        this.player1 = UserPlayer( Piece.RED, Piece.RED_DOT )
        this.player2 = ComPlayer( Piece.YELLOW, Piece.YELLOW_DOT )
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

    val piecesInHandPlayer1
        get() = player1.getAmountOfPieces()
    val piecesInHandPlayer2
        get() = player2.getAmountOfPieces()

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
            if( activePlayer.hasPiecesInHand() ) {
                val newMove = activePlayer.makeMove()
                val wouldGain = this.wouldGainPiece(newMove.instructions, activePlayer)
                if (wouldGain) {
                    //activePlayer.usePiece()
                    board.applyMove(newMove)
                    //activePlayer.gainPiece()
                } else {
                    activePlayer.usePiece()
                    board.applyMove(newMove)
                }
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
        activePlayer = if(activePlayer.piece == player1.piece) {
            currentPlayer++
            player2
        }
        else {
            currentPlayer--
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
     * Method to check if a Player would gain a Piece in a Move
     *
     * @param move move to check
     * @param player player to check fo
     * @return true if Player would gain a piece on that move, else false
     */
    private fun wouldGainPiece(move: MoveInstruction, player: Player) : Boolean {
        return this.board.wouldGainPiece( move, player )
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