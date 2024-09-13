import entities.*
import map.Map
import entities.Entities.*
import entities.Entities.Grass
import entities.Entities.Rock
import entities.Entities.Tree
import map.MapRenderer

private const val START_LOOP_SIMULATION_CODE =  1
private const val NEXT_TURN_SIMULATION_CODE = 2
private const val STOP_SIMULATION_CODE = 3
private val MAX_AMOUNT_OF_ENTITIES : kotlin.collections.Map<String, Int> = mapOf(
    "Rabbit" to 10,
    "Wolf" to 4,
    "Rock" to 10,
    "Tree" to 10,
    "Grass" to 20)

class Simulation {
    private var amountOfTurns : Int = 0
    private lateinit var map : Map
   private val gameData = GameData()

    fun startSimulation() {
        initSimulation()
        printGameInfo()
        startGameLoop()
    }

    private fun nextTurn() {
        amountOfTurns++
        turnActions()
        println( gameData.getCurrentGameStats(map))
    }



    private fun printMap(){
        MapRenderer().renderMap(map)
    }


    private fun printGameInfo(){
        println("Текущий ход: $amountOfTurns")
        printMap()
        println("Введите $START_LOOP_SIMULATION_CODE, чтобы запустить цикл симуляции")
        println("Введите $NEXT_TURN_SIMULATION_CODE, чтобы запустить следующий ход симуляции")
        println("Введите $STOP_SIMULATION_CODE, чтобы остановть симуляцию")
        println()


    }

    private fun startGameLoop(){
        while (true){
            nextTurn()
            val userInput = readln()
            if (userInput == "0"){
                break
            }
            printGameInfo()
        }
    }

    private fun pauseSimulation() {

    }

    private fun initSimulation(){
        map = Map()
        map.createMap()
        initAllTypesOfEntities(map)
    }

    private fun initAllTypesOfEntities(map: Map){
        for (entity in Entities.entries){
            when(entity){
                Rabbit -> {createEntity(Herbivore())}
                Wolf -> {createEntity(Predator())}
                Rock -> {createEntity(entities.Rock())}
                Tree -> {createEntity(entities.Tree())}
                Grass -> {createEntity(entities.Grass())}
            }
        }
    }

    private fun checkExistenceFoodForCreatures(){

    }

    private fun createEntity(entity: Entity){
        val amountOfEntities  = MAX_AMOUNT_OF_ENTITIES[entity.entityName]
        if(amountOfEntities is Int){
            for (i in 1..amountOfEntities) {
                map.createEntityOnRandomCoordinates(entity)
            }
        }
    }

    private fun turnActions() {
        val currentMap = map.getMap().toMap() // Create a copy of the map
        val iterator = currentMap.entries.iterator()
        while (iterator.hasNext()) {
            val (coordinate, entity) = iterator.next()

            if (entity is Herbivore) {
                map = entity.makeMove(coordinate, map)
            } else if (entity is Predator) {
                map = entity.makeMove(coordinate, map)
            }
        }
    }
    
}

fun main() {
    val game = Simulation()
    game.startSimulation()
}