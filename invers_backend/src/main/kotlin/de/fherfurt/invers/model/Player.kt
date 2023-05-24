package de.fherfurt.invers.model

import de.fherfurt.invers.core.Piece

abstract class Player protected constructor(val piece: Piece, val dottedPiece: Piece) {

    abstract fun makeMove(index: Int, piece: Piece)
}