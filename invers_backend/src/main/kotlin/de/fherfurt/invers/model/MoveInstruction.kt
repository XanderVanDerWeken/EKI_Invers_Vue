package de.fherfurt.invers.model

import de.fherfurt.invers.core.Direction

data class MoveInstruction(
    val direction: Direction,
    val index: Int
)
