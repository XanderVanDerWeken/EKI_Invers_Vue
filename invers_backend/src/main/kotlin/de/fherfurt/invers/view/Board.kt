package de.fherfurt.invers.view

import de.fherfurt.invers.core.Direction
import de.fherfurt.invers.core.Piece
import de.fherfurt.invers.model.Move
import de.fherfurt.invers.model.MoveInstruction
import de.fherfurt.invers.model.Player

open class Board (val pieces: MutableList<Piece>) {

    constructor() : this( initializePieces() )

    companion object BoardUtils {
        private const val offsetInArray: Int = 11

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
    }

    /**
     * Method to apply a move.
     * It is replacing from last to first index
     *
     * @param move move to apply
     */
    fun applyMove(move: Move) {
        // calculate index of last position
        var index = getLastIndex(move.direction, move.index)

        // While not running into border
        while (pieces[index] != Piece.BORDER) {
            val prevIndex = index + move.direction.previousIndex

            if(pieces[prevIndex] != Piece.BORDER) { // Case In Field
                pieces[index] = pieces[prevIndex]
            }
            else { // Case colliding into border
                pieces[index] = move.piece
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
    fun getAllLegal(opponentDottedPiece: Piece) : List<MoveInstruction> {
        val result = mutableListOf<MoveInstruction>()

        for( i in 1..6) {
            if( isLegal( i, Direction.UP,    opponentDottedPiece ) ) result.add( MoveInstruction( Direction.UP, i ) )
            if( isLegal( i, Direction.DOWN,  opponentDottedPiece ) ) result.add( MoveInstruction( Direction.DOWN, i ) )
            if( isLegal( i, Direction.LEFT,  opponentDottedPiece ) ) result.add( MoveInstruction( Direction.LEFT, i ) )
            if( isLegal( i, Direction.RIGHT, opponentDottedPiece ) ) result.add( MoveInstruction( Direction.RIGHT, i ) )
        }

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
        val lastIndex = getLastIndex(direction, index)
        return pieces[lastIndex] != opponentDottedPiece
    }

    /**
     * Method to count the amount of pieces of a player on a board
     *
     * @param player player to count for
     * @return Amount of pieces
     */
    fun countPlayerPieces( player: Player ) : Int {
        return this.countPieces( player.piece, player.dottedPiece )
    }

    /**
     * Method to check if a given Board of pieces is game over
     *
     * @return true if Game is over, else false
     */
    fun isGameOver() : Boolean {
        val amountDottedRed = countPieces(Piece.RED_DOT)
        val amountDottedYellow = countPieces(Piece.YELLOW_DOT)

        return amountDottedRed == 18 || amountDottedYellow == 18
    }

    /**
     * Method to count Pieces in the board
     *
     * @param piecesToCount piecesToCount as vararg parameter
     * @return amount of pieces
     */
    protected fun countPieces(vararg piecesToCount: Piece) : Int {
        return pieces.count { piece ->
            piecesToCount.contains( piece )
        }
    }
}