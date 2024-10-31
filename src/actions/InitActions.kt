package actions

import entities.Entities
import entities.Entities.*
import entities.Herbivore
import entities.Predator
import map.Map

class InitActions : Actions() {
    private val AMOUNT_OF_ENTITIES_FOR_SPAWN : kotlin.collections.Map<String, Int> = mapOf(
        "Rabbit" to 10,
        "Wolf" to 4,
        "Rock" to 10,
        "Tree" to 10,
        "Grass" to 40)

    fun createSimulationMap() : Map {
        val map = Map()
        map.createMap()
        initAllTypesOfEntities(map)
        return map
    }

    private fun initAllTypesOfEntities(map : Map){
        for (entity in Entities.entries){
            when(entity){
                Rabbit -> {createEntity(map, "Rabbit")}
                Wolf -> {createEntity(map, "Wolf")}
                Rock -> {createEntity(map, "Rock")}
                Tree -> {createEntity(map, "Tree")}
                Grass -> {createEntity(map, "Grass")}
            }
        }
    }

    private fun createEntity(map : Map,entity: String) {
        val amountOfEntities = AMOUNT_OF_ENTITIES_FOR_SPAWN[entity]
        // TODO try replace Int to not null if
        if (amountOfEntities is Int) {
            for (i in 1..amountOfEntities) {
                when (entity) {
                    "Rabbit" -> map.createEntityOnRandomCoordinates(Herbivore())
                    "Wolf" -> map.createEntityOnRandomCoordinates(Predator())
                    "Rock" -> map.createEntityOnRandomCoordinates(entities.Rock())
                    "Tree" -> map.createEntityOnRandomCoordinates(entities.Tree())
                    "Grass" -> map.createEntityOnRandomCoordinates(entities.Grass())
                }
            }
        }
    }
}