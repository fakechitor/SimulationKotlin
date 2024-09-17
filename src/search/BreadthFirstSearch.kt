package search

import entities.*
import map.AMOUNT_OF_COLUMNS
import map.AMOUNT_OF_ROWS
import map.Coordinates
import map.Map
import java.util.*
import kotlin.math.max

private const val MINIMAL_COORDINATE = 1
private val MAXIMAL_COORDINATE = max(AMOUNT_OF_ROWS, AMOUNT_OF_COLUMNS)

class BreadthFirstSearch {
    private var checkedCells : MutableList<Pair<Int,Int>> = mutableListOf()
    private val queue : Queue<Pair<Int, Int>> = LinkedList()
    private val occupiedCells : MutableList<Pair<Int,Int>> = mutableListOf()

    fun bfs(startCoordinates: Coordinates, map: Map, requiredEntity: String): List<Pair<Int, Int>>? {
        val creature = checkTypeOfCreature(startCoordinates, map)
        val pairOfStartCoordinates = startCoordinates.getCoordinates()
        val parentMap = mutableMapOf<Pair<Int, Int>, Pair<Int, Int>?>()
        queue.add(pairOfStartCoordinates)
        checkedCells.add(pairOfStartCoordinates)
        parentMap[pairOfStartCoordinates] = null
        var f = false
        while (queue.isNotEmpty()) {
            val current = queue.poll()
            val cellStatus = map.getCellStatus(Coordinates(current.first, current.second))

            if (f == true && isCellOccupied(current, cellStatus, creature)) {
                continue
            }
            else if (cellStatus.toString() == requiredEntity) {
                return reconstructPath(parentMap, current)
            }
            val linkedNodes = makeLinkedNodesUnique(current)
            for (node in linkedNodes) {
                checkedCells.add(node)
                queue.add(node)
                parentMap[node] = current
                f = true
            }
        }
        return null
    }

    private fun isCellOccupied(coordinatesPair: Pair<Int, Int>, currentCell : Any?, creature : String) : Boolean{
        val setOfObstacles = getObstaclesForCell(creature)
        if (currentCell.toString() in setOfObstacles){
            occupiedCells.add(coordinatesPair)
            return true
        }
        return false
    }

    private fun getObstaclesForCell(creature: String): Set<String> {
        var setOfObstacles = setOf<Any>()
        val newSetOfObstacles = mutableSetOf<String>()
        if (creature == "Herbivore"){
            setOfObstacles = HerbivoreObstacles.entries.toSet()
        }
        else{
            setOfObstacles = PredatorObstacles.entries.toSet()
        }
        for (obstacle in setOfObstacles){
            newSetOfObstacles.add(obstacle.toString())
        }
        return newSetOfObstacles
    }

    private fun checkTypeOfCreature(startCoordinates: Coordinates, map: Map) : String{
        val cellStatus = map.getCellStatus(startCoordinates)
        if (cellStatus.toString() == "Rabbit"){
            return "Herbivore"
        }
        return "Predator"
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