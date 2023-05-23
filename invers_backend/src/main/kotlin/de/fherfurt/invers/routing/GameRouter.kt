package de.fherfurt.invers.routing

import de.fherfurt.invers.controller.Game
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable

object GameRouter {

    fun Route.gameRoutes() {
        route("/game") {
            get("/play") {
                Game.playGame()
                call.respond(HttpStatusCode.OK, "Game was started")
            }
            get("/score") {
                val result = Score(Game.scorePlayer1, Game.scorePlayer2)
                call.respond(result)
            }
        }
    }

    @Serializable
    data class Score(val scorePlayer1: Int, val scorePlayer2: Int)
}