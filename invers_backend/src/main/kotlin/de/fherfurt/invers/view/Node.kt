package de.fherfurt.invers.view

import de.fherfurt.invers.core.Piece
import de.fherfurt.invers.model.Move
import de.fherfurt.invers.model.MoveInstruction

class Node (
    currentBoard: ComplayerBoard
) {
    val board = ComplayerBoard( currentBoard.pieces.toMutableList() )
    val childNodes = mutableListOf<Node>()
    var weight = 0

    fun getAllLegalFromNode( opponentPiece: Piece ) : List<MoveInstruction> {
        return board.getAllLegal( opponentPiece )
    }

    fun createChildAndAdd( moveInstruction: MoveInstruction, ownPieces: Pair<Piece, Piece> ): Node {
        val childNode = Node( this.board )
        board.executeMove( Move(moveInstruction, ownPieces.second), ownPieces )
        this.childNodes.add( childNode )
        return childNode
    }

    fun evaluateBoard(ownPieces: Pair<Piece, Piece>, oppPieces: Pair<Piece, Piece>): Int {
        return board.evaluateBoard(ownPieces, oppPieces)
    }

    fun isTerminal(): Boolean {
        return board.isGameOver()
    }
}