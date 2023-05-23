package de.fherfurt.invers

import de.fherfurt.invers.routing.GameRouter.gameRoutes
import de.fherfurt.invers.routing.PlayerRouter.playerRoutes
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.jetty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json

object InversServer {

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

fun Application.restApplicationModule() {
    install(ContentNegotiation) {
        json(Json{
            prettyPrint = true
            isLenient = true
        })
    }

    routing {
        playerRoutes()
        gameRoutes()
    }
}