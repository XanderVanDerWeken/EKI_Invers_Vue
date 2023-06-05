package de.fherfurt.invers.model

import de.fherfurt.invers.controller.Game
import de.fherfurt.invers.core.Piece
import de.fherfurt.invers.view.Board
import javax.swing.text.StyledEditorKit.BoldAction

/**
 * Player using an AI to play
 *
 * @see Player
 * @author Xander Van der Weken
 */
class ComPlayer(piece: Piece, dottedPiece: Piece) : Player(piece, dottedPiece) {

    private val opponentDottedPiece: Piece
        get() =
            if(dottedPiece == Piece.RED_DOT) Piece.YELLOW_DOT
            else Piece.YELLOW_DOT

    companion object {
        val maxDepth = 3
    }

    /**
     * Method to make a move.
     * It is waiting while no move is set through the API.
     * It is calculating the move with a min max algorithm
     *
     * @return move to be made and applied on the Board
     */
    override fun makeMove() : Move {
        // Build Game tree
        val root = Node()
        buildGameTree(root, maxDepth, true)

        // Max-Min Turns

        TODO("Not yet implemented")
    }

    /**
     * Method to build a tree for all possible scenarios.
     * It is recursive, to allow multiple layers
     *
     * @param node node to build on
     * @param depth depth that is remaining to build
     * @param isRootNode true if node is root
     */
    private fun buildGameTree(node: Node, depth: Int, isRootNode: Boolean) {
        // Cancel Criteria
        if (depth <= 0) {
            return
        }

        // Building Board for Scenario
        val scenarioBoard: MutableList<Piece> = if (isRootNode) Game.board()
            else Board.applyMovesOnCopy(Game.board(), node.moves)

        // Get next piece for Scenario
        val scenarioPiece: Piece = if (depth % 2 != 0) dottedPiece
            else opponentDottedPiece

        // Get Possible moves
        val possibleMoves = Board.getAllLegal(scenarioBoard, opponentDottedPiece)

        // Add Nodes child nodes to node, and calling recursion
        possibleMoves.forEach { entry ->
            entry.value.forEach { index ->
                val childNode = Node(node.moves)
                    .addMove(entry.key, index, scenarioPiece)
                node.addChild(childNode)
                buildGameTree(childNode, depth - 1, false)
            }
        }
    }
}