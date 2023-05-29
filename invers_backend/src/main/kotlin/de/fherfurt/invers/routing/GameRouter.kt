package de.fherfurt.invers.routing

import de.fherfurt.invers.controller.Game
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.*
import kotlinx.serialization.Serializable
import java.lang.Exception

object GameRouter {
    private val gameScope = CoroutineScope(Dispatchers.Default)

    fun Route.gameRoutes() {
        route("/game") {
            post("/play") {
                gameScope.launch {
                    try {
                        Game.playGame()
                    } catch (e: Exception) {
                        println("Coroutine exception: ${e.message}")
                    }
                }
                call.respond(HttpStatusCode.OK, "Game was started")
            }
            post("/reset") {
                gameScope.cancel()
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
    private data class Score(
        val scorePlayer1: Int,
        val scorePlayer2: Int,
        val activePlayer: Int
    )
}