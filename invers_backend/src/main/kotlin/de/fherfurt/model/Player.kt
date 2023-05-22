package de.fherfurt.model

import de.fherfurt.core.Piece

abstract class Player protected constructor(val piece: Piece, val dottedPiece: Piece) {

    abstract fun makeMove(index: Int, piece: Piece)
}