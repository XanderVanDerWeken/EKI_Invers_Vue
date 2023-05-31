package de.fherfurt.invers.routing

import de.fherfurt.invers.controller.Game
import de.fherfurt.invers.core.Direction
import de.fherfurt.invers.model.Move
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
                val move = Move( direction, index )
                if( Game.activePlayer is UserPlayer ) {
                    (Game.activePlayer as UserPlayer).updateMove( move )
                    call.respond(HttpStatusCode.OK)
                }
                else {
                    call.respond(HttpStatusCode.BadRequest)
                }
            }
            get("/isLegal/{direction}/{index}") {
                val direction = Direction.valueOf( call.parameters["direction"]!! )
                val index = call.parameters["index"]!!.toInt()

                call.respond(HttpStatusCode.OK, Game.isLegalMove( index, direction ))
            }
            get("/possibleMoves") {
                val result = mutableListOf<Moves>()
                Game.getLegalMoves().map { entry ->
                    result.add(Moves(entry.key, entry.value))
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