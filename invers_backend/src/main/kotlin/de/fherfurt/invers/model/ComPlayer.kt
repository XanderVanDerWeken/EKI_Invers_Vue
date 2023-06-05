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

    override fun makeMove() : Move {
        // Build Game tree
        val root = Node()
        buildGameTree(root, maxDepth, true)

        // Max-Min Turns

        TODO("Not yet implemented")
    }

    private fun buildGameTree(node: Node, depth: Int, isRootNode: Boolean) {
        if (depth <= 0) {
            return
        }

        val scenarioBoard: MutableList<Piece> = if (isRootNode) Game.board()
            else Board.applyMovesOnCopy(Game.board(), node.moves)

        val scenarioPiece: Piece = if (depth % 2 != 0) dottedPiece
            else opponentDottedPiece

        val possibleMoves = Board.getAllLegal(scenarioBoard, opponentDottedPiece)
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