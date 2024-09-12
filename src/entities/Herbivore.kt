package entities

import map.Coordinates
import map.Map
import map.MapRenderer
import search.BreadthFirstSearch

private const val HERBIVORE_SPEED = 3
private const val HERBIVORE_HEALTH_POINTS = 10
private const val HERBIVORE_ATTACK_RANGE = 3


class Herbivore() : Creature() {
    override var amountOfSpeed = HERBIVORE_SPEED
    override var healthPoints  = HERBIVORE_HEALTH_POINTS
    override val entityName = "Rabbit"
    override fun makeMove(startCoordinates: Coordinates, map: map.Map): map.Map {
        var tempMap : Map = map
        val pathForGrass = BreadthFirstSearch().bfs(startCoordinates, map, "Grass")

        val coordinatesForNextMove: Pair<Int, Int>
        if (!pathForGrass.isNullOrEmpty()) {
            if (pathForGrass.size <= HERBIVORE_ATTACK_RANGE) {
                coordinatesForNextMove = pathForGrass.last()
                tempMap = eatFood(startCoordinates, map, Coordinates(coordinatesForNextMove.first, coordinatesForNextMove.second))
            } else {
                coordinatesForNextMove = pathForGrass[HERBIVORE_SPEED - 1]
                val currentEntity = map.getMap()[startCoordinates]
                if (currentEntity is Creature) {
                    tempMap.setEntity(currentEntity, Coordinates(coordinatesForNextMove.first, coordinatesForNextMove.second))
                }
            }
        }
        return tempMap


    }

    override fun eatFood(startCoordinates: Coordinates, map: Map, newCoordinates: Coordinates) : map.Map {
        val currentEntity = map.getMap()[startCoordinates]
        if (currentEntity != null && currentEntity is Creature) {
            map.setEntity(currentEntity,newCoordinates)
            map.setEntity("",startCoordinates)
        }
        return map
    }
}