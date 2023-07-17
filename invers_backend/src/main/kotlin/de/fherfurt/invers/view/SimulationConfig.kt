package de.fherfurt.invers.view

import de.fherfurt.invers.model.Player

class SimulationConfig (
    val player1: Player,
    val player2: Player
) {
    val weightNormalPiece: Int = 5
    val weightDottedPiece: Int = 1
}
