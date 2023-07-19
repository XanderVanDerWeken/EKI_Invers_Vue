package de.fherfurt.invers.routing

import de.fherfurt.invers.controller.Game
import de.fherfurt.invers.core.Direction
import de.fherfurt.invers.model.MoveInstruction
import de.fherfurt.invers.model.UserPlayer
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable

/**
 * Player Router Object, which is providing the Endpoints for the Players.
 * It is handling all Endpoints connected to User Interaction
 *
 * @author Xander Van der Weken
 */
object PlayerRouter {
    /**
     * Extension of Route for Players
     */
    fun Route.playerRoutes() {
        route("/players") {
            post("/makeMove/{direction}/{index}") {
                val direction = Direction.valueOf( call.parameters["direction"]!!.uppercase() )
                val index = call.parameters["index"]!!.toInt()

                // handle Move
                val move = MoveInstruction( direction, index )
                if( Game.activePlayer is UserPlayer ) {
                    if(Game.isLegalMove(move)) {
                        (Game.activePlayer as UserPlayer).updateMove( move )
                        call.respond(HttpStatusCode.OK, "Valid Move")
                    }
                    else {
                        call.respond(HttpStatusCode.OK, "Invalid Move")
                    }
                }
                else {
                    call.respond(HttpStatusCode.BadRequest)
                }
            }
            get("/isLegal/{direction}/{index}") {
                val direction = Direction.valueOf( call.parameters["direction"]!! )
                val index = call.parameters["index"]!!.toInt()

                val move = MoveInstruction(direction, index)
                call.respond(HttpStatusCode.OK, Game.isLegalMove( move ))
            }
            get("/possibleMoves") {
                val groupedMap = Game.getLegalMoves().groupBy(MoveInstruction::direction) { it.index }
                val result = groupedMap.map{ (direction, indexes) ->
                    Moves(direction, indexes)
                }
                call.respond(HttpStatusCode.OK, result)
            }
        }
    }

    /**
     * Private Serializable Data Class representing all legal Moves
     *
     * @author Xander Van der Weken
     */
    @Serializable
    private data class Moves (
        val direction: Direction,
        val indexes: List<Int>
    )
}