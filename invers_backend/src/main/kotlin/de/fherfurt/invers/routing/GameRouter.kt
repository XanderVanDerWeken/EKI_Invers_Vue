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
            post("/play") {
                Game.playGame()
                call.respond(HttpStatusCode.OK, "Game was started")
            }
            post("/reset") {
                Game.resetBoard()
                call.respond(HttpStatusCode.OK, "Game war resetted")
            }
            get("/stats") {
                val result = Score(Game.scorePlayer1, Game.scorePlayer2, Game.currentPlayer())
                call.respond(result)
            }
            get("/board") {
                val board = Game.board().toList()
                call.respond(HttpStatusCode.OK, board)
            }
        }
    }

    @Serializable
    private data class Score(val scorePlayer1: Int, val scorePlayer2: Int, val activePlayer: Int)
}