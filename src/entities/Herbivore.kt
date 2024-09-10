package entities

import map.Coordinates
import map.Map
import search.BreadthFirstSearch

private const val HERBIVORE_SPEED = 3
private const val HERBIVORE_HEALTH_POINTS = 10
private const val HERBIVORE_ATTACK_RANGE = 3


class Herbivore(name : String) : Creature() {
    override var amountOfSpeed = HERBIVORE_SPEED
    override var healthPoints  = HERBIVORE_HEALTH_POINTS
    override val entityName = name
    override fun makeMove(startCoordinates: Coordinates, map: map.Map) {
        val pathForGrass = BreadthFirstSearch().bfs(startCoordinates, map, "Grass")
        val coordinatesForNextMove : Pair<Int,Int>
        if (!pathForGrass.isNullOrEmpty()) {
            if (pathForGrass.size <= HERBIVORE_ATTACK_RANGE){
                coordinatesForNextMove = pathForGrass.last()
                eatFood(startCoordinates, map, Coordinates(coordinatesForNextMove.first, coordinatesForNextMove.second))
            }
            else{
                coordinatesForNextMove = pathForGrass[HERBIVORE_SPEED - 1]
                val currentEntity = map.getMap()[startCoordinates]
                if (currentEntity is Creature) {
                    map.setEntity(currentEntity, Coordinates(coordinatesForNextMove.first, coordinatesForNextMove.second))
                }
            }
        }



    }

    override fun eatFood(startCoordinates: Coordinates, map: Map, newCoordinates: Coordinates) {
        val currentEntity = map.getMap()[startCoordinates]
        println(map.getMap())
        println("1423")
        print(map.getMap())
        if (currentEntity != null) {
            map.setEntity(currentEntity,newCoordinates)
            map.setEntity("",startCoordinates)
        }
    }
}