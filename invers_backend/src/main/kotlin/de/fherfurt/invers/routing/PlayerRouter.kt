package de.fherfurt.invers.routing

import de.fherfurt.invers.core.Direction
import io.ktor.server.application.*
import io.ktor.server.routing.*

object PlayerRouter {
    fun Route.playerRoutes() {
        route("/players") {
            post("/makeMove/{col}/{row}/{direction}") {
                val col = call.parameters["col"]!!.toInt()
                val row = call.parameters["row"]!!.toInt()
                val direction = call.parameters["direction"]!! to Direction::class

                // handle Move
            }
        }
    }
}