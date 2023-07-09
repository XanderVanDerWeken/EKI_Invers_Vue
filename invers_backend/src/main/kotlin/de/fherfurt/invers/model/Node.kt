package de.fherfurt.invers.model

import de.fherfurt.invers.controller.Game
import de.fherfurt.invers.core.Direction
import de.fherfurt.invers.core.Piece
import de.fherfurt.invers.view.Board
import de.fherfurt.invers.view.SimulationBoard

/**
 * Class representing a Node in the Min-Max-Tree
 *
 * @author Xander Van der Weken
 */
class Node(val moves: MutableList<Move>) {
    val childNodes: MutableList<Node> = mutableListOf()
    var weight: Int = 0

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

    /**
     * Method to check if a Node is terminal.
     * A Node is terminal, if the state is game over or max depth is reached
     *
     * @return true if node is Terminal, else false
     */
    fun isTerminal() : Boolean {
        val gameOver = Board.isGameOver(pieces = Board.applyMovesOnCopy(Game.board(), this.moves))

        return gameOver || childNodes.isEmpty()
    }

    /**
     * Method to evaluate the Board.
     * It creates the scenario Board and counts the scores
     *
     * @param ownPiece own dotted Piece
     * @param opponentPiece opponent dotted Piece
     * @return difference of ownPiece and opponentPiece
     */
    fun evaluateBoard(ownPiece: Piece, opponentPiece: Piece) : Int {
        val scenarioBoard = Board.applyMovesOnCopy(Game.board(), this.moves)
        //val ownScore = Board.countPieces(scenarioBoard, ownPiece)
        //val opponentScore = Board.countPieces(scenarioBoard, opponentPiece)
        var ownScore: Int
        var opponentScore: Int
        if(ownPiece == Piece.RED || ownPiece == Piece.RED_DOT) {
            ownScore = SimulationBoard.getScoreWithStrategy(scenarioBoard, Piece.RED_DOT, Piece.RED_DOT)
            opponentScore = SimulationBoard.getScoreWithStrategy(scenarioBoard, Piece.YELLOW, Piece.YELLOW_DOT)
        }
        else {
            ownScore = SimulationBoard.getScoreWithStrategy(scenarioBoard, Piece.YELLOW, Piece.YELLOW_DOT)
            opponentScore = SimulationBoard.getScoreWithStrategy(scenarioBoard, Piece.RED, Piece.RED_DOT)
        }


        return ownScore - opponentScore
    }
}
