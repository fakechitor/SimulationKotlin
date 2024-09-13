package entities

import Simulation
import map.Coordinates
import map.Map
import search.BreadthFirstSearch

private const val PREDATOR_SPEED = 2
private const val PREDATOR_HEALTH_POINTS = 16
private const val PREDATOR_ATTACK_RANGE = 2

class Predator() : Creature() {
    override var amountOfSpeed = PREDATOR_SPEED
    override var healthPoints  = PREDATOR_HEALTH_POINTS
    override val entityName = "Wolf"
    override fun makeMove(startCoordinates: Coordinates, map: map.Map): Map {
        var tempMap : Map = map
        val pathForGrass = BreadthFirstSearch().bfs(startCoordinates, map, "Rabbit")
        val coordinatesForNextMove: Pair<Int, Int>
        if (!pathForGrass.isNullOrEmpty()) {
            if (pathForGrass.size <= PREDATOR_ATTACK_RANGE) {
                coordinatesForNextMove = pathForGrass.last()
                tempMap = eatFood(startCoordinates, map, Coordinates(coordinatesForNextMove.first, coordinatesForNextMove.second))
            } else {
                coordinatesForNextMove = pathForGrass[PREDATOR_SPEED - 1]
                val currentEntity = map.getMap()[startCoordinates]
                if (currentEntity is Creature) {
                    tempMap.setEntity(currentEntity, Coordinates(coordinatesForNextMove.first, coordinatesForNextMove.second))
                }
            }
        }
        return tempMap
    }

    override fun eatFood(startCoordinates: Coordinates, map: Map, newCoordinates: Coordinates): Map {
        val newMap = map
        val currentEntity = map.getMap()[startCoordinates]
        if (currentEntity != null && currentEntity is Creature) {
            newMap.setEntity(currentEntity,newCoordinates)
            newMap.setEntity("",startCoordinates)
        }
        return newMap
    }
}