package de.fherfurt.invers.view

import de.fherfurt.invers.core.Direction
import de.fherfurt.invers.core.Piece
import de.fherfurt.invers.model.Player

class Board {
    val pieces: MutableList<Piece>

    init {
        this.pieces = initializePieces()
    }

    fun pushLine(row: Int, col: Int, direction: Direction) {

    }

    /**
     * Method to check if a cell is valid
     *
     * @param row row of field
     * @param col column of field
     * @param player player to look for valid
     * @return true if cell is valid, else false
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

    private fun initializePieces(): MutableList<Piece> {
        val pieces = mutableListOf<Piece>()
        var nextRed = true
        for(i in 0..99 ) {
            // Border Above & Below
            if(i < 20 || i >= 80) {
                pieces.add(Piece.BORDER)
            }
            else {
                val col = i % 10
                val row = (i / 10).toInt()
                // Border Left and Right
                if(col == 0 ||col == 1 || col == 8 || col == 9) {
                    pieces.add( Piece.BORDER )
                }
                else { // Inner Fields
                    if((col + row) % 2 == 0) {
                        pieces.add( Piece.RED )
                    } else {
                        pieces.add( Piece.YELLOW )
                    }
                }
            }
        }
        return pieces
    }

    private fun countPieces(piece: Piece): Int   {
        return pieces.count{ it == piece }
    }

    private fun rowAndColToIndex(row: Int, col: Int): Int {
        return row * 10 + col
    }
}