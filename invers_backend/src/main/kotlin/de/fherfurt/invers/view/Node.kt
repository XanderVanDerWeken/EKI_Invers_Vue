package de.fherfurt.invers.view

import de.fherfurt.invers.core.Piece
import de.fherfurt.invers.model.Move
import de.fherfurt.invers.model.MoveInstruction

class Node (
    currentBoard: ComplayerBoard
) {
    private val board = ComplayerBoard( currentBoard.pieces.toMutableList() )
    private val childNodes = mutableListOf<Node>()
    var moveInstruction: MoveInstruction? = null
    var weight = 0

    fun getAllLegalFromNode( opponentPiece: Piece ) : List<MoveInstruction> {
        return board.getAllLegal( opponentPiece )
    }

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

    fun evaluateBoard(ownPieces: Pair<Piece, Piece>, oppPieces: Pair<Piece, Piece>): Int {
        return board.evaluateBoard(ownPieces, oppPieces)
    }

    fun isTerminal(): Boolean {
        return board.isGameOver()
    }

    fun getChildNodes() : List<Node> = this.childNodes
}
