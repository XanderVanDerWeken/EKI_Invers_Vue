package de.fherfurt.invers.view

import de.fherfurt.invers.core.Piece
import de.fherfurt.invers.model.Move
import de.fherfurt.invers.model.MoveInstruction

/**
 * Class representing a Node on the Min Max Algorithm
 *
 * @author Xander Van der Weken
 */
class Node (
    currentBoard: ComplayerBoard
) {
    private val board = ComplayerBoard( currentBoard.pieces.toMutableList() )
    private val childNodes = mutableListOf<Node>()
    var moveInstruction: MoveInstruction? = null
    var weight = 0

    /**
     * Method to get all Legal Moves for the current board.
     *
     * @param opponentPiece opponentPiece to check for validness
     * @return List of MoveInstructions
     */
    fun getAllLegalFromNode( opponentPiece: Piece ) : List<MoveInstruction> {
        return board.getAllLegal( opponentPiece )
    }

    /**
     * Method to create a Child Node with a move, and add it to the parent node list.
     *
     * @param moveInstruction moveInstruction to execute
     * @param ownPieces ownPieces to use for move
     * @return created child node
     */
    fun createChildAndAdd( moveInstruction: MoveInstruction, ownPieces: Pair<Piece, Piece> ): Node {
        // Creating Child
        val childNode = Node( this.board )
        childNode.moveInstruction = moveInstruction
        // Executing Move
        board.executeMove( Move(moveInstruction, ownPieces.second), ownPieces )
        // Add child to Parent
        this.childNodes.add( childNode )
        return childNode
    }

    /**
     * Method to evaluate the current Board.
     *
     * @param ownPieces ownPieces own pair of pieces
     * @param oppPieces oppPieces opponent pair of pieces
     * @return Int with score
     */
    fun evaluateBoard(ownPieces: Pair<Piece, Piece>, oppPieces: Pair<Piece, Piece>): Int {
        return board.evaluateBoard(ownPieces, oppPieces)
    }

    /**
     * Method to check if the Node is terminal.
     *
     * @return true if is Terminal else false
     */
    fun isTerminal(): Boolean {
        return board.isGameOver() || childNodes.isEmpty()
    }

    /**
     * Getter for childNodes
     *
     * @return list of Child Nodes
     */
    fun getChildNodes() : List<Node> = this.childNodes

    override fun toString(): String {
        return toJsonString(this)
    }

    /**
     * Method to take a node and transform it to a json String
     *
     * @param node node to serialize to json
     * @return json string
     */
    private fun toJsonString(node: Node): String {
        val json = StringBuilder()

        json.append("{")

        json.append("\"moveInstruction\": \"${node.moveInstruction}\",")
        json.append("\"weight\": ${node.weight},")

        if( node.childNodes.isNotEmpty() ) {
            json.append("\"childNodes\": [")
            node.childNodes.forEachIndexed { index, child ->
                json.append(toJsonString( child ))
                if( index < node.childNodes.size - 1 ) {
                    json.append(",")
                }
            }
            json.append("]")
        }

        json.append("}")

        return json.toString()
    }
}
