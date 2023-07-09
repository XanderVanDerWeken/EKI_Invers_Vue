package de.fherfurt.invers.view

import de.fherfurt.invers.controller.Game
import de.fherfurt.invers.core.Piece
import de.fherfurt.invers.model.ComPlayer
import de.fherfurt.invers.model.Move
import de.fherfurt.invers.model.Node

class SimulationBoard (pieces: MutableList<Piece>, dottedPiece: Piece, opponentDottedPiece: Piece) {
    val pieces: MutableList<Piece>
    val dottedPiece: Piece
    val opponentDottedPiece: Piece

    init {
        this.pieces = pieces
        this.dottedPiece = dottedPiece
        this.opponentDottedPiece = opponentDottedPiece
    }

    companion object SimulationBoardUtils {
        private val min_inf = Int.MIN_VALUE
        val strategy: List<Int> = listOf(
            min_inf, min_inf, min_inf, min_inf, min_inf, min_inf, min_inf, min_inf, min_inf, min_inf,
            min_inf, min_inf, min_inf, min_inf, min_inf, min_inf, min_inf, min_inf, min_inf, min_inf,
            min_inf, min_inf,     120,      10,      10,      10,      10,     120, min_inf, min_inf,
            min_inf, min_inf,      10,       1,       1,       1,       1,      10, min_inf, min_inf,
            min_inf, min_inf,      10,       1,       1,       1,       1,      10, min_inf, min_inf,
            min_inf, min_inf,      10,       1,       1,       1,       1,      10, min_inf, min_inf,
            min_inf, min_inf,      10,       1,       1,       1,       1,      10, min_inf, min_inf,
            min_inf, min_inf,     120,      10,      10,      10,      10,     120, min_inf, min_inf,
            min_inf, min_inf, min_inf, min_inf, min_inf, min_inf, min_inf, min_inf, min_inf, min_inf,
            min_inf, min_inf, min_inf, min_inf, min_inf, min_inf, min_inf, min_inf, min_inf, min_inf,
        )
    }

    fun getBestMove(): Move {
        // Build Game tree
        val root = Node()
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

    /**
     * Method to run the graph, calculating the weight in the terminal nodes, and min-max it up to the root Node.
     * It is build recursive, to allow simple usage.
     *
     * @param node node to calculate the weights for
     * @param maximizingPlayer maximizingPlayer Flag, if the turn should be maximized or minimized
     */
    private fun calculateWeights(node: Node, maximizingPlayer: Boolean) {
        if(node.isTerminal()) {
            node.weight = node.evaluateBoard(this.dottedPiece, opponentDottedPiece)
            return
        }

        val children = node.childNodes

        if(maximizingPlayer) { // Maximize the weight
            var maxWeight = Int.MIN_VALUE

            children.forEach { child ->
                calculateWeights(child, false)
                maxWeight = maxOf(maxWeight, child.weight)
            }

            node.weight = maxWeight
        }
        else { // Minimize the weight
            var minWeight = Int.MAX_VALUE

            children.forEach{ child ->
                calculateWeights(child, true)
                minWeight = minOf(minWeight, child.weight)
            }

            node.weight = minWeight
        }
    }

    /**
     * Method to get the Move with the given weight from the children of the node
     *
     * @param node node to get the move for
     * @param weight weight to look for
     * @return move with given weight.
     */
    private fun getMoveWithWeight(root: Node, weight: Int) : Move {
        /*var move: Move? = null

        node.childNodes.forEach { child ->
            if (child.weight == weight) {
                move = child.moves[0]
            }
        }

        return move!!*/

        val allPossibleMoves: List<Node> = root.childNodes.filter { child ->
            child.weight == weight
        }

        val bestNode = if(allPossibleMoves.size > 1) {
            allPossibleMoves.random()
        }
        else {
            allPossibleMoves[0]
        }
        return bestNode.moves[0]
    }
}