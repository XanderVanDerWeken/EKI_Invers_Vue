package de.fherfurt.invers.model

import de.fherfurt.invers.controller.Game
import de.fherfurt.invers.core.Direction
import de.fherfurt.invers.core.Piece
import de.fherfurt.invers.view.Board

class Node(val moves: MutableList<Move>) {
    val childNodes: MutableList<Node> = mutableListOf()

    constructor() : this(mutableListOf<Move>())

    fun addChild( node: Node ) : Node {
        childNodes.add( node )
        return this
    }

    fun addMove( direction: Direction, index: Int, piece: Piece ) : Node {
        this.moves.add( Move(direction, index, piece) )
        return this
    }

    fun getWeight(player: Player) : Int {
        val copy = Board.applyMovesOnCopy(Game.board(), moves)
        return Board.getPlayerScore(copy, player)
    }
}