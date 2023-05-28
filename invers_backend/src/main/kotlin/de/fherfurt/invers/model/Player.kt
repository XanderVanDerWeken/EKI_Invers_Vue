package de.fherfurt.invers.model

import de.fherfurt.invers.core.Piece

abstract class Player protected constructor(
    val piece: Piece,
    val dottedPiece: Piece,
) {

    private var amountPieces = 18
    private var amountDottedPieces = 0

    abstract fun makeMove()

    protected fun amountPieces(): Int {
        return amountPieces
    }

    protected fun amountDottedPieces(): Int {
        return amountDottedPieces
    }

    protected fun gainDottedPieces(amount: Int) {
        amountDottedPieces += amount
    }

    fun useDottedPiece(amount: Int) {
        amountDottedPieces++
    }

    fun usePieces(amount: Int) {
        amountPieces++
    }
}