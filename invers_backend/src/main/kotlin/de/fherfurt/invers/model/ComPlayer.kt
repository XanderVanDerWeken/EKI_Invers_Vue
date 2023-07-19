package de.fherfurt.invers.model

import de.fherfurt.invers.controller.Game
import de.fherfurt.invers.core.Piece
import de.fherfurt.invers.view.Board
import de.fherfurt.invers.view.ComplayerBoard
import de.fherfurt.invers.view.Node

/**
 * Player using an AI to play
 *
 * @see Player
 * @author Xander Van der Weken
 */
class ComPlayer(piece: Piece, dottedPiece: Piece) : Player(piece, dottedPiece, aiPlayerKind) {

    private val opponentPieces: Pair<Piece, Piece>
        get() =
            if(dottedPiece == Piece.RED_DOT) Pair(Piece.YELLOW, Piece.YELLOW_DOT)
            else Pair(Piece.RED, Piece.RED_DOT)
    private val ownPieces: Pair<Piece, Piece>
        get() =
            if(dottedPiece == Piece.RED_DOT) Pair(Piece.RED, Piece.RED_DOT)
            else Pair(Piece.YELLOW, Piece.YELLOW_DOT)

    companion object {
        const val aiPlayerKind = "AI Player"
        const val maxDepth = 3
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
        val root = Node( ComplayerBoard( Game.board() ) )
        buildGameTree(root, ComPlayer.maxDepth, true)

        // Starting Point for calculating Weights
        calculateWeights(root, true)

        val rootWeight = root.weight

        // Get Move From Child with weight
        return getMoveWithWeight(root, rootWeight)
    }

    /**
     * Method to build a tree for all possible scenarios.
     * It is recursive, to allow multiple layers
     *
     * @param node node to build on
     * @param depth depth that is remaining to build
     * @param isRootNode true if node is root
     */
    private fun buildGameTree(node: Node, depth: Int, isRootNode: Boolean = false) {
        // Cancel Criteria
        if (depth <= 0) {
            return
        }

        // Variable to set for the current step
        val situationOpponentDottedPiece: Piece =
            if( depth % 2 != 0 ) opponentPieces.second
            else ownPieces.second

        val situationOwnPieces: Pair<Piece, Piece> =
            if( depth % 2 != 0 ) ownPieces
            else opponentPieces

        // Get possible Moves and adding the child nodes
        val possibleMoves = node.getAllLegalFromNode( situationOpponentDottedPiece )
        possibleMoves.forEach { moveInstruction ->
            val childNode = node.createChildAndAdd( moveInstruction, situationOwnPieces )
            buildGameTree( childNode, depth - 1 )
        }
    }

    /**
     * Method to run the graph, calculating the weight in the terminal nodes, and min-max it up to the root Node.
     * It is build recursive, to allow simple usage.
     *
     * @param node node to calculate the weights for
     * @param maximizingPlayer maximizingPlayer Flag, if the turn should be maximized or minimized
     */
    private fun calculateWeights(node: Node, maximizingPlayer: Boolean) {
        if(node.isTerminal()) {
            node.weight = node.evaluateBoard(ownPieces, opponentPieces)
            return
        }

        val children = node.childNodes

        if(maximizingPlayer) { // Maximize the weight
            var maxWeight = Int.MIN_VALUE

            children.forEach { child ->
                calculateWeights(child, false)
                maxWeight = maxOf(maxWeight, child.weight)
            }
        }
        else { // Minimize the weight
            var minWeight = Int.MAX_VALUE

            children.forEach{ child ->
                calculateWeights(child, true)
                minWeight = minOf(minWeight, child.weight)
            }
        }
    }

    /**
     * Method to get the Move with the given weight from the children of the node
     *
     * @param node node to get the move for
     * @param weight weight to look for
     * @return move with given weight.
     */
    private fun getMoveWithWeight(node: Node, weight: Int) : Move {

    }
}