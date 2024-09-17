package entities

import map.Coordinates
import map.Map
import search.BreadthFirstSearch

private const val HERBIVORE_SPEED = 3
private const val HERBIVORE_MAX_HEALTH_POINTS = 12
private const val HERBIVORE_ATTACK_RANGE = HERBIVORE_SPEED
private const val HP_INCREASE_BY_FOOD = 3
private const val HP_DECREASE_BY_HUNGER = 1


class Herbivore() : Creature() {
    override var amountOfSpeed = HERBIVORE_SPEED
    override var healthPoints  = HERBIVORE_MAX_HEALTH_POINTS
    override val entityName = "Rabbit"
    override fun makeMove(startCoordinates: Coordinates, map: map.Map): map.Map {
        var tempMap : Map = map
        val pathForGrass = BreadthFirstSearch().bfs(startCoordinates, map, "Grass")
        val coordinatesForNextMove: Pair<Int, Int>
        if (!pathForGrass.isNullOrEmpty()) {
            val currentEntity = map.getMap()[startCoordinates]
            if (pathForGrass.size <= HERBIVORE_ATTACK_RANGE) {
                coordinatesForNextMove = pathForGrass.last()
                tempMap = eatFood(startCoordinates, map, Coordinates(coordinatesForNextMove.first, coordinatesForNextMove.second))
            } else {
                coordinatesForNextMove = pathForGrass[HERBIVORE_SPEED]
                if (currentEntity is Creature) {
                    tempMap.setEntity(currentEntity,Coordinates(coordinatesForNextMove.first, coordinatesForNextMove.second))
                    tempMap.setEntity("",startCoordinates)
                }
            }
        }
        return tempMap


    }

    override fun eatFood(startCoordinates: Coordinates, map: Map, newCoordinates: Coordinates): map.Map {
        this.healthPoints += HP_INCREASE_BY_FOOD
        if (healthPoints > HERBIVORE_MAX_HEALTH_POINTS){
            healthPoints = HERBIVORE_MAX_HEALTH_POINTS
        }
        val currentEntity = map.getMap()[startCoordinates]
        if (currentEntity != null && currentEntity is Creature) {
            map.setEntity(currentEntity, newCoordinates)
            map.setEntity("", startCoordinates)
        }
        return map
    }

    override fun decreaseHealthPointsByHunger() {
        this.healthPoints -= HP_DECREASE_BY_HUNGER
    }
}