package de.fherfurt.invers.controller

import de.fherfurt.invers.view.Node
import java.io.File

/**
 * Controller to log in Files
 *
 * @author Xander Van der Weken
 */
class FileController {

    init {
        val logFolder = File("logs")
        if (!logFolder.exists()) {
            logFolder.mkdir()
        }
    }

    /**
     * Method to save a Graph to the logs Folder
     *
     * @param node root node to save the graph
     */
    fun saveGraph(node: Node) {
        val filepath = "logs/graph_${System.currentTimeMillis()}.json"
        File( filepath ).writeText(node.toString())
    }
}
