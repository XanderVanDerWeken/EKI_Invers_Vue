package de.fherfurt.invers.view

import de.fherfurt.invers.core.Direction
import de.fherfurt.invers.core.Piece
import de.fherfurt.invers.model.Move
import de.fherfurt.invers.model.MoveInstruction
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

    /**
     * Companion Object for Board class.
     * It is holding onto all utils methods for Boards.
     * This Abstraction making it possible, to apply it on lists, for the AI
     *
     * @see Board
     * @author Xander Van der Weken
     */
    companion object BoardUtils {
        private const val offsetInArray: Int = 11

        /**
         * Method to apply a move.
         * It is replacing from last to first index
         *
         * @param pieces pieces List where the Move should be applied on
         * @param move move to apply
         */
        fun applyMove(pieces: MutableList<Piece>, move: Move) {
            // calculate index of last position
            var index = this.getLastIndex(move.direction, move.index)

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
         * Method to apply multiple moves on a copy of a list
         *
         * @param pieces original pieces list
         * @param moves moves to execute
         * @return copied list with moves executed on
         */
        fun applyMovesOnCopy(pieces: List<Piece>, moves: List<Move>) : MutableList<Piece> {
            val copyList = pieces.toMutableList()
            moves.forEach { move ->
                applyMove(copyList, move)
            }
            return copyList
        }

        /**
         * Method to get all Legal Moves
         *
         * @param pieces pieces List where the legal Moves are asked for
         * @param opponentDottedPiece opponentDottedPiece
         * @return Map of Direction and lists of possible indexes
         */
        fun getAllLegal(pieces: List<Piece>, opponentDottedPiece: Piece): Map<Direction, List<Int>> {
            // Create Lists
            val result = HashMap<Direction, List<Int>>()
            val upList = mutableListOf<Int>()
            val downList = mutableListOf<Int>()
            val leftList = mutableListOf<Int>()
            val rightList = mutableListOf<Int>()

            // Check for all directions, all cols and rows
            for( i in 1..6 ) {
                if( isLegal(pieces, i, Direction.UP,    opponentDottedPiece ) ) upList.add( i )
                if( isLegal(pieces, i, Direction.DOWN,  opponentDottedPiece ) ) downList.add( i )
                if( isLegal(pieces, i, Direction.LEFT,  opponentDottedPiece ) ) leftList.add( i )
                if( isLegal(pieces, i, Direction.RIGHT, opponentDottedPiece ) ) rightList.add( i )
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
         * @param pieces pieces List where to check for validness
         * @param index index to push
         * @param direction direction to push
         * @param opponentDottedPiece opponentDottedPiece dotted Piece of the Opponent Player
         */
        fun isLegal(pieces: List<Piece>, index: Int, direction: Direction, opponentDottedPiece: Piece): Boolean {
            val lastIndex = this.getLastIndex(direction, index)
            return pieces[lastIndex] != opponentDottedPiece
        }

        /**
         * Method to check if a Player would gain a piece on a move
         *
         * @param pieces pieces where to check
         * @param move move to check for
         * @param player player to check for
         * @return true if a Piece would be gained, else false
         */
        fun wouldGainPiece(pieces: List<Piece>, move: MoveInstruction, player: Player) : Boolean {
            val lastIndex = this.getLastIndex(move.direction, move.index)
            return pieces[lastIndex] == player.piece || pieces[lastIndex] == player.dottedPiece
        }

        /**
         * Method to calculate the player Score
         *
         * @param pieces pieces where to get the score from
         * @param player player for which to calculate the score
         * @return score of player
         */
        fun getPlayerScore(pieces: List<Piece>, player: Player) : Int {
            return countPieces(pieces, player.dottedPiece)
        }

        /**
         * Method to count a kind of pieces on the Board
         *
         * @param pieces pieces where to count
         * @param countingPiece countingPiece which to count
         * @return score of counted Piece
         */
        fun countPieces( pieces: List<Piece>, countingPiece: Piece ) : Int {
            return pieces.count { piece ->
                piece == countingPiece
            }
        }

        fun countPlayerPieces( pieces: List<Piece>, player: Player ) : Int {
            return pieces.count { piece ->
                piece == player.piece || piece == player.dottedPiece
            }
        }

        /**
         * Method to check if a given Board of pieces is game over
         *
         * @param pieces pieces to check
         * @return true if Game is over, else false
         */
        fun isGameOver( pieces: List<Piece> ) : Boolean {
            val amountDottedRed = countPieces(pieces, Piece.RED_DOT)
            val amountDottedYellow = countPieces(pieces, Piece.YELLOW_DOT)

            return amountDottedRed == 18 || amountDottedYellow == 18
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

    /**
     * Method to apply a move.
     * It is replacing from last to first index
     *
     * @param move move to apply
     */
    fun applyMove(move: Move) {
        return BoardUtils.applyMove(this.pieces, move)
    }

    /**
     * Method to get all Legal Moves
     *
     * @param opponentDottedPiece opponentDottedPiece
     * @return Map of Direction and lists of possible indexes
     */
    fun getAllLegal(opponentDottedPiece: Piece): Map<Direction, List<Int>> {
        return BoardUtils.getAllLegal(this.pieces, opponentDottedPiece)
    }

    /**
     * Method to check if a move is valid
     *
     * @param index index to push
     * @param direction direction to push
     * @param opponentDottedPiece opponentDottedPiece dotted Piece of the Opponent Player
     */
    fun isLegal(index: Int, direction: Direction, opponentDottedPiece: Piece): Boolean {
        return BoardUtils.isLegal(this.pieces, index, direction, opponentDottedPiece)
    }

    /**
     * Method to check if a Player would gain a piece on a move
     *
     * @param move move to check for
     * @param player player to check for
     * @return true if a Piece would be gained, else false
     */
    fun wouldGainPiece(move: MoveInstruction, player: Player) : Boolean {
        return BoardUtils.wouldGainPiece(this.pieces, move, player)
    }

    /**
     * Method to calculate the player Score
     *
     * @param player player for which to calculate the score
     * @return score of player
     */
    fun getPlayerScore(player: Player) : Int {
        return BoardUtils.getPlayerScore(this.pieces, player)
    }

    fun countPlayerPieces( player: Player ) : Int {
        return BoardUtils.countPlayerPieces( this.pieces, player )
    }

    /**
     * Method to check if this Board is Game Over
     *
     * @return true if Game is over, else false
     */
    fun isGameOver() : Boolean {
        return BoardUtils.isGameOver(this.pieces)
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