import map.Map
import entities.Entities
import entities.Entities.*
import entities.Entity
import entities.Herbivore
import entities.Predator
import entities.Rock
import map.MapRenderer
import kotlin.random.Random

private val MAX_AMOUNT_OF_ENTITIES : kotlin.collections.Map<String, Int> = mapOf(
    "Rabbit" to 10,
    "Wolf" to 4,
    "Rock" to 10,
    "Tree" to 10,
    "Grass" to 20)
fun main() {
    MAX_AMOUNT_OF_ENTITIES["Rabbit"]
}

class Simulation {
    private var amountOfTurns : Int = 0
    private lateinit var map : Map
    private var entityData : MutableList<Entity> = mutableListOf()
    private fun nextTurn() {
        amountOfTurns++
    }
    fun printMap(){
        MapRenderer().renderMap(map)
    }
    fun startSimulation() {
        initSimulation()
    }
    fun pauseSimulation() {

    }

    private fun initSimulation(){
        map = Map()
        map.createMap()
        initAllTypesOfEntities(map)

    }

    private fun initAllTypesOfEntities(map: Map){
        for (entity in Entities.entries){
            when(entity){
                Rabbit -> {createEntity(Herbivore("Rabbit"))}
                Wolf -> {createEntity(Predator("Wolf"))}
                Rock -> {createEntity(Rock("Rock"))}
                Tree -> {createEntity(Herbivore("Tree"))}
                Grass -> {createEntity(Herbivore("Grass"))}
            }
        }
    }

    private fun createEntity(entity: Entity){
        val amountOfEntities  = MAX_AMOUNT_OF_ENTITIES[entity.entityName]
        if(amountOfEntities is Int){
            for (i in 1..amountOfEntities) {
            map.createEntityOnRandomCoordinates(entity)
        }
        }
    }

    fun turnActions(){
        while (true){
            nextTurn()
        }
    }

    private fun checkGameStats(){

    }
    
}
