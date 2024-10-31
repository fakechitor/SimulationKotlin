package actions

import entities.Creature
import entities.Herbivore
import entities.Predator
import map.Coordinates
import map.Map

class TurnActions : Actions() {
    fun turnActions(map : Map) {
        val currentMap = map.map.toMap()
        val iterator = currentMap.entries.iterator()
        while (iterator.hasNext()) {
            val (coordinate, entity) = iterator.next()
            if (entity is Herbivore) {
                entity.decreaseHealthPointsByHunger()
                removeDiedEntities(map, coordinate, entity)
                entity.makeMove(coordinate, map)
            }
            else if (entity is Predator) {
                entity.decreaseHealthPointsByHunger()
                removeDiedEntities(map, coordinate, entity)
                entity.makeMove(coordinate, map)
            }
        }
    }
    private fun removeDiedEntities(map : Map,coordinates: Coordinates, entity : Creature){
        if (entity.healthPoints <= 0 ){
            map.setEntity("",coordinates)
        }
    }
}