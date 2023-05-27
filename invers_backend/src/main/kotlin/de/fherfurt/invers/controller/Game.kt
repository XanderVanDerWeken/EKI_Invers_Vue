package de.fherfurt.invers.controller

import de.fherfurt.invers.core.Direction
import de.fherfurt.invers.core.Piece
import de.fherfurt.invers.model.Player
import de.fherfurt.invers.model.UserPlayer
import de.fherfurt.invers.view.Board
import java.util.*

object Game {
    private val player1: Player
    private val player2: Player
    private val activePlayer: Player
    private var board: Board
    private var currentPlayer: Int

    init {
        this.player1 = UserPlayer( Piece.RED, Piece.RED_DOT )
        this.player2 = UserPlayer( Piece.YELLOW, Piece.YELLOW_DOT )
        this.activePlayer = this.player1
        this.board = Board()
        this.currentPlayer = 1
    }

    val scorePlayer1
        get() = board.getPlayerScore( player1 )
    val scorePlayer2
        get() = board.getPlayerScore( player2 )
    private val opponentDottedPiece
        get() =
            if( activePlayer.dottedPiece === Piece.RED_DOT ) Piece.YELLOW_DOT
            else Piece.RED_DOT



    fun playGame() {

    }

    fun switchPlayer() {

    }

    fun isGameOver() {
        
    }

    fun getLegalMoves() : Map<Direction, List<Int>> {
        return this.board.getAllLegal(opponentDottedPiece)
    }

    fun isLegalMove( index: Int, direction: Direction) : Boolean {
        return this.board.isLegal(index, direction, opponentDottedPiece)
    }

    fun resetBoard() {
        this.board = Board()
    }

    fun board() : MutableList<Piece> {
        return this.board.pieces
    }

    fun currentPlayer(): Int {
        return this.currentPlayer
    }
}