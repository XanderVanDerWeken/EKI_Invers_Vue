package de.fherfurt.invers

import de.fherfurt.invers.routing.GameRouter.gameRoutes
import de.fherfurt.invers.routing.PlayerRouter.playerRoutes
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.jetty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json

/**
 * Server Application, which is holding on the Server and is the Entry Point to the Jar
 *
 * @author Xander Van der Weken
 */
object InversServer {

    /**
     * Main Method and Entry Point.
     *
     * @param args args from the command line
     */
    @JvmStatic
    fun main(args: Array<String>) {
        embeddedServer(Jetty, port = 8080, configure = {
            connectionGroupSize = 2
            workerGroupSize = 5
            callGroupSize = 10
            shutdownGracePeriod = 2000
            shutdownTimeout = 3000
        }, module = Application::restApplicationModule).start(wait = true)
    }
}

/**
 * Extension from Application, for this server Module.
 * It is installing Content Serialization and CORS, and controlling the routing
 */
fun Application.restApplicationModule() {
    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
            isLenient = true
        })
    }

    install(CORS) {
        allowMethod(HttpMethod.Delete)
        allowMethod(HttpMethod.Post)
        allowMethod(HttpMethod.Get)
        allowHeader(HttpHeaders.Authorization)
        anyHost()
    }

    routing {
        playerRoutes()
        gameRoutes()
    }
}