package de.fherfurt.invers.routing

import de.fherfurt.invers.controller.Game
import de.fherfurt.invers.core.Direction
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable

object PlayerRouter {
    fun Route.playerRoutes() {
        route("/players") {
            post("/makeMove/{col}/{row}/{direction}") {
                val col = call.parameters["col"]!!.toInt()
                val row = call.parameters["row"]!!.toInt()
                val direction = call.parameters["direction"]!! to Direction::class

                // handle Move
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

    @Serializable
    private data class Moves (
        val direction: Direction, val indexes: List<Int>
    )
}