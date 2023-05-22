package de.fherfurt.view

import de.fherfurt.core.Direction
import de.fherfurt.core.Piece
import de.fherfurt.model.Player
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
}