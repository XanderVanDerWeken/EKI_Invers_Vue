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
    val offsetInArray: Int = 11

    init {
        this.pieces = initializePieces()
    }

    /**
     * Method to apply a move.
     * It is replacing from last to first index
     *
     * @param newPiece newPiece to be shifted in
     * @param newIndex newIndex where the piece is shifted in
     * @param direction direction the piece is shifted
     */
    fun applyMove(newPiece: Piece, newIndex: Int, direction: Direction) {
        // calculate index of last position
        var index = this.getLastIndex(direction, newIndex)

        // While not running into border
        while (pieces[index] != Piece.BORDER) {
            val prevIndex = index + direction.previousIndex

            if(pieces[prevIndex] != Piece.BORDER) { // Case In Field
                pieces[index] = pieces[prevIndex]
            }
            else { // Case colliding into border
                pieces[index] = newPiece
            }
            index = prevIndex
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
        val lastIndex = this.getLastIndex(direction, index)
        return pieces[lastIndex] != opponentDottedPiece
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
     * Helper Method to get the last index in direction
     *
     * @param direction direction to shift
     * @param newIndex index from col or row, from frontend, with 1 <= newIndex <= 6
     * @return last Index of direction
     */
    private fun getLastIndex(direction: Direction, newIndex: Int) : Int {
        return when (direction) {
            Direction.UP -> {
                rowAndColToIndex(1, newIndex)
            }
            Direction.DOWN -> {
                rowAndColToIndex(6, newIndex)
            }
            Direction.LEFT -> {
                rowAndColToIndex(newIndex, 1)
            }
            Direction.RIGHT -> {
                rowAndColToIndex(newIndex, 6)
            }
        }
    }

    /**
     * Method to map the row and col index from UI to Index in table
     *
     * @param row rowindex
     * @param col colindex
     * @return index
     */
    private fun rowAndColToIndex(row: Int, col: Int): Int {
        return row * 10 + col + offsetInArray
    }
}