package de.fherfurt.invers.controller

import de.fherfurt.invers.view.Node
import java.io.File

class FileController {

    init {
        val logFolder = File("logs")
        if (!logFolder.exists()) {
            logFolder.mkdir()
        }
    }

    fun saveGraph(node: Node) {
        val filepath = "logs/graph_${System.currentTimeMillis()}.json"
        File( filepath ).writeText(node.toString())
    }
}
