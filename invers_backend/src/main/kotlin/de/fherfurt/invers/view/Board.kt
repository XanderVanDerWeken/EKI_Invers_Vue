package de.fherfurt.invers.view

import de.fherfurt.invers.core.Direction
import de.fherfurt.invers.core.Piece
import de.fherfurt.invers.model.Player

/**
 * Class representing a Board.
 * It is holding the list and performing all operations on the board.
 *
 * @author Xander Van der Weken
 */
class Board {
    val pieces: MutableList<Piece>

    init {
        this.pieces = initializePieces()
    }

    private fun pushRow(newPiece: Piece, rowIndex: Int, direction: Direction) {
        val row = rowIndex + 1
        if (direction === Direction.RIGHT) {

        }
        else {

        }
    }

    /**
     * Method to get all Legal Moves
     *
     * @param opponentDottedPiece opponentDottedPiece
     * @return Map of Direction and lists of possible indexes
     */
    fun getAllLegal(opponentDottedPiece: Piece): Map<Direction, List<Int>> {
        // Create Lists
        val result = HashMap<Direction, List<Int>>()
        val upList = mutableListOf<Int>()
        val downList = mutableListOf<Int>()
        val leftList = mutableListOf<Int>()
        val rightList = mutableListOf<Int>()

        // Check for all directions, all cols and rows
        for( i in 1..6 ) {
            if( isLegal(i, Direction.UP,    opponentDottedPiece ) ) upList.add( i )
            if( isLegal(i, Direction.DOWN,  opponentDottedPiece ) ) downList.add( i )
            if( isLegal(i, Direction.LEFT,  opponentDottedPiece ) ) leftList.add( i )
            if( isLegal(i, Direction.RIGHT, opponentDottedPiece ) ) rightList.add( i )
        }

        // Add everything to result and return
        result[Direction.UP] =  upList
        result[Direction.DOWN] = downList
        result[Direction.LEFT] =  leftList
        result[Direction.RIGHT] = rightList
        return result
    }

    /**
     * Method to check if a move is valid
     *
     * @param index index to push
     * @param direction direction to push
     * @param opponentDottedPiece opponentDottedPiece dotted Piece of the Opponent Player
     */
    fun isLegal(index: Int, direction: Direction, opponentDottedPiece: Piece): Boolean {
        return when(direction) {
            Direction.UP -> {
                val col = index + 1
                val topIndexInCol = 20 + col
                pieces[topIndexInCol] !== opponentDottedPiece
            }
            Direction.DOWN -> {
                val col = index + 1
                val botIndexInCol = 70 + col
                pieces[botIndexInCol] !== opponentDottedPiece
            }
            Direction.LEFT -> {
                val row = index + 1
                val firstIndexInRow = row * 10 + 2
                pieces[firstIndexInRow] !== opponentDottedPiece
            }
            Direction.RIGHT -> {
                val row = index + 1
                val lastIndexInRow = row * 10 + 7
                pieces[lastIndexInRow] !== opponentDottedPiece
            }
        }
    }

    /**
     * Method to calculate the player Score
     *
     * @param player player for which to calculate the score
     * @return score of player
     */
    fun getPlayerScore(player: Player) : Int {
        return pieces.count { piece ->
            piece == player.dottedPiece
        }
    }

    /**
     * Method to initialize the board
     *
     * @return List of Pieces
     */
    private fun initializePieces(): MutableList<Piece> {
        val pieces = mutableListOf<Piece>()
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

    /**
     * Method to map the row and col index from UI to Index in table
     *
     * @param row rowindex
     * @param col colindex
     * @return index
     */
    private fun rowAndColToIndex(row: Int, col: Int): Int {
        return row * 10 + col
    }
}