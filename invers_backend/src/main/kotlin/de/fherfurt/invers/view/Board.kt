package de.fherfurt.invers.view

import de.fherfurt.invers.core.Direction
import de.fherfurt.invers.core.Piece
import de.fherfurt.invers.model.Player
import java.util.*

class Board {
    val pieces: Vector<Piece>

    init {
        this.pieces = initializePieces()
    }

    fun pushLine(row: Int, col: Int, direction: Direction) {

    }

    fun flipField(row: Int, col: Int) {

    }

    /**
     * Method to check if a cell is valid
     */
    fun isValid(row: Int, col: Int, player: Player): Boolean {
        val index = rowAndColToIndex(row, col)
        val neighborIndex = index + 1
        return ( pieces[index] == Piece.EMPTY &&
                (pieces[neighborIndex] == player.piece || pieces[neighborIndex] == player.dottedPiece)
        )
    }

    fun getPlayerScore(player: Player) : Int {
        return pieces.count { piece ->
            piece == player.piece || piece == player.dottedPiece
        }
    }

    private fun initializePieces(): Vector<Piece> {
        val pieces = Vector<Piece>()
        for(i in 0..99 ) {
            // Border Above & Below
            if(i < 20 || i >= 80) {
                pieces.add(Piece.BORDER)
            }
            else {
                val col = i % 10
                // Border Left and Right
                if(col == 0 ||col == 1 || col == 8 || col == 9) {
                    pieces.add( Piece.BORDER )
                }
                else { // Inner Empty
                    pieces.add(Piece.EMPTY)
                }
            }
        }
        return pieces
    }

    private fun rowAndColToIndex(row: Int, col: Int): Int {
        return row * 10 + col
    }
}