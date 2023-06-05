package de.fherfurt.invers.model

import de.fherfurt.invers.controller.Game
import de.fherfurt.invers.core.Direction
import de.fherfurt.invers.core.Piece
import de.fherfurt.invers.view.Board

/**
 * Class representing a Node in the Min-Max-Tree
 *
 * @author Xander Van der Weken
 */
class Node(val moves: MutableList<Move>) {
    val childNodes: MutableList<Node> = mutableListOf()

    constructor() : this(mutableListOf<Move>())

    /**
     * Method to add a child Node
     *
     * @param node node to add as a child
     * @return itself, for method chaining
     */
    fun addChild( node: Node ) : Node {
        childNodes.add( node )
        return this
    }

    /**
     * Method to add a Move to this node
     *
     * @param direction direction for move
     * @param index index for move
     * @param piece piece for move
     * @return itself, for method chaining
     */
    fun addMove( direction: Direction, index: Int, piece: Piece ) : Node {
        this.moves.add( Move(direction, index, piece) )
        return this
    }

    fun getWeight(player: Player) : Int {
        val copy = Board.applyMovesOnCopy(Game.board(), moves)
        return Board.getPlayerScore(copy, player)
    }
}