package de.fherfurt.invers.routing

import de.fherfurt.invers.controller.Game
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.*
import kotlinx.serialization.Serializable
import java.lang.Exception

/**
 * Game Router Object, which is providing the Endpoints for the Game.
 * It is handling all Endpoints connected to controlling the Game and fetching Board Data.
 *
 * @author Xander Van der Weken
 */
object GameRouter {
    /**
     * Coroutine Scope for the Game Loop
     */
    private val gameScope = CoroutineScope(Dispatchers.Default)

    /**
     * Extension of Route for Game
     */
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
                call.respond(HttpStatusCode.OK, "Game was resetted")
            }
            get("/stats") {
                val result = Score(
                    scorePlayer1 = Game.scorePlayer1,
                    scorePlayer2 = Game.scorePlayer2,
                    activePlayer = Game.currentPlayer
                )
                call.respond(result)
            }
            get("/options") {
                val result = Options(
                    kindPlayerOne =  Game.kindPlayer1,
                    kindPlayerTwo =  Game.kindPlayer2,
                    colorPlayerOne =  Game.colorPlayer1,
                    colorPlayerTwo =  Game.colorPlayer2
                )
                call.respond(result)
            }
            get("/board") {
                val board = Game.board().toList()
                call.respond(HttpStatusCode.OK, board)
            }
        }
    }

    /**
     * Private Serializable Data Class representing all Score Data
     *
     * @author Xander Van der Weken
     */
    @Serializable
    private data class Score(
        val scorePlayer1: Int,
        val scorePlayer2: Int,
        val activePlayer: Int
    )

    @Serializable
    private data class Options(
        val kindPlayerOne: String,
        val kindPlayerTwo: String,
        val colorPlayerOne: String,
        val colorPlayerTwo: String
    )
}