package search

import map.Coordinates
import map.Map
import java.util.*

private const val MINIMAL_COORDINATE = 1
private const val MAXIMAL_COORDINATE = 10

class BreadthFirstSearch {
    private var checkedCells : MutableList<Pair<Int,Int>> = mutableListOf()
    private val queue : Queue<Pair<Int, Int>> = LinkedList()
    private val occupiedCells : MutableList<Pair<Int,Int>> = mutableListOf()

    fun bfs(startCoordinates: Coordinates, map: Map, requiredEntity: String): List<Pair<Int, Int>>? {
        val pairOfStartCoordinates = startCoordinates.getCoordinates()
        val parentMap = mutableMapOf<Pair<Int, Int>, Pair<Int, Int>?>()
        queue.add(pairOfStartCoordinates)
        checkedCells.add(pairOfStartCoordinates)
        parentMap[pairOfStartCoordinates] = null

        while (queue.isNotEmpty()) {
            val current = queue.poll()
            val cellStatus = map.getCellStatus(Coordinates(current.first, current.second))
            if (cellStatus in Obstacles.entries){
                occupiedCells.add(Pair(current.first,current.second))
                continue
            }
            else if (cellStatus == requiredEntity) {
                return reconstructPath(parentMap, current)
            }
            val linkedNodes = makeLinkedNodesUnique(current)
            for (node in linkedNodes) {
                checkedCells.add(node)
                queue.add(node)
                parentMap[node] = current
            }
        }
        return null
    }

    private fun getLinkedNodes(coordinatesPair : Pair<Int,Int>) : List<Pair<Int,Int>>{
        var listOfNodes = listOf<Pair<Int,Int>>()
        if (coordinatesPair.first > MINIMAL_COORDINATE) {
            listOfNodes = listOfNodes.plus(Pair(coordinatesPair.first - 1,coordinatesPair.second))
        }
        if (coordinatesPair.first < MAXIMAL_COORDINATE) {
            listOfNodes = listOfNodes.plus(Pair(coordinatesPair.first + 1,coordinatesPair.second))
        }
        if (coordinatesPair.second > MINIMAL_COORDINATE) {
            listOfNodes = listOfNodes.plus(Pair(coordinatesPair.first,coordinatesPair.second-1))
        }
        if (coordinatesPair.second < MAXIMAL_COORDINATE) {
            listOfNodes = listOfNodes.plus(Pair(coordinatesPair.first,coordinatesPair.second+1))
        }
        return listOfNodes
    }

    private fun makeLinkedNodesUnique(coordinatesPair: Pair<Int, Int>): MutableList<Pair<Int, Int>> {
        val listOfNodes = getLinkedNodes(coordinatesPair)
        val listOfUniqueNodes: MutableList<Pair<Int, Int>> = mutableListOf()
        for (item in listOfNodes) {
            if (item !in checkedCells && item !in queue) {
                listOfUniqueNodes.add(item)
            }
        }
        return listOfUniqueNodes
    }

    private fun reconstructPath(parentMap: MutableMap<Pair<Int, Int>, Pair<Int, Int>?>, endCoordinates: Pair<Int, Int>): List<Pair<Int, Int>> {
        val path = mutableListOf<Pair<Int, Int>>()
        var current: Pair<Int, Int>? = endCoordinates

        while (current != null) {
            if (current !in occupiedCells) {
                path.add(0, current)
            }
            current = parentMap[current]
        }
        return path
    }
}