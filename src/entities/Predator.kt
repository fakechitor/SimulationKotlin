package entities

import Simulation
import map.Coordinates
import map.Map
import search.BreadthFirstSearch

private const val PREDATOR_SPEED = 2
private const val PREDATOR_HEALTH_POINTS = 16
private const val PREDATOR_ATTACK_RANGE = 2

class Predator(name : String) : Creature() {
    override var amountOfSpeed = PREDATOR_SPEED
    override var healthPoints  = PREDATOR_HEALTH_POINTS
    override val entityName = name
    override fun makeMove(startCoordinates: Coordinates, map: map.Map): Map {
        val pathForHerbivore = BreadthFirstSearch().bfs(startCoordinates, map, "Rabbit")
        if (pathForHerbivore != null) {
            val coordinatesForNextMove = pathForHerbivore[0]
        }
        else{

        }
        return map
    }

    override fun eatFood(startCoordinates: Coordinates, map: Map, newCoordinates: Coordinates): Map {
        TODO("Not yet implemented")
    }
}