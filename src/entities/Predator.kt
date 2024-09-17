package entities

import map.Coordinates
import map.Map
import search.BreadthFirstSearch

private const val PREDATOR_SPEED = 1
private const val PREDATOR_MAX_HEALTH_POINTS = 20
private const val PREDATOR_ATTACK_RANGE = PREDATOR_SPEED
private const val HP_INCREASE_BY_FOOD = 8
private const val HP_DECREASE_BY_HUNGER = 1

class Predator() : Creature() {
    override var amountOfSpeed = PREDATOR_SPEED
    override var healthPoints  = PREDATOR_MAX_HEALTH_POINTS
    override val entityName = "Wolf"
    override fun makeMove(startCoordinates: Coordinates, map: map.Map): Map {
        var tempMap : Map = map
        val pathForHerbivore = BreadthFirstSearch().bfs(startCoordinates, map, "Rabbit")
        val coordinatesForNextMove: Pair<Int, Int>
        if (!pathForHerbivore.isNullOrEmpty()) {
            if (pathForHerbivore.size <= PREDATOR_ATTACK_RANGE) {
                coordinatesForNextMove = pathForHerbivore.last()
                tempMap = eatFood(startCoordinates, map, Coordinates(coordinatesForNextMove.first, coordinatesForNextMove.second))
            } else {
                coordinatesForNextMove = pathForHerbivore[PREDATOR_SPEED]
                val currentEntity = map.getMap()[startCoordinates]
                if (currentEntity is Creature) {
                    tempMap.setEntity(currentEntity,Coordinates(coordinatesForNextMove.first, coordinatesForNextMove.second))
                    tempMap.setEntity("",startCoordinates)
                }
            }
        }
        return tempMap
    }

    override fun eatFood(startCoordinates: Coordinates, map: Map, newCoordinates: Coordinates): Map {
        this.healthPoints += HP_INCREASE_BY_FOOD
        if (healthPoints > PREDATOR_MAX_HEALTH_POINTS){
            healthPoints = PREDATOR_MAX_HEALTH_POINTS
        }
        val newMap = map
        val currentEntity = map.getMap()[startCoordinates]
        if (currentEntity != null && currentEntity is Creature) {
            newMap.setEntity(currentEntity,newCoordinates)
            newMap.setEntity("",startCoordinates)
        }
        return newMap
    }

    override fun decreaseHealthPointsByHunger() {
        this.healthPoints -= HP_DECREASE_BY_HUNGER
    }
}