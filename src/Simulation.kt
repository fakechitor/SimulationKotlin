import entities.*
import map.Map
import entities.Entities.*
import entities.Entities.Grass
import entities.Entities.Rock
import entities.Entities.Tree
import map.MapRenderer
import kotlin.random.Random

// Codes for process user input
private const val START_LOOP_SIMULATION_CODE =  1
private const val NEXT_TURN_SIMULATION_CODE = 2
private const val STOP_SIMULATION_CODE = 3

private val AMOUNT_OF_ENTITIES_FOR_SPAWN : kotlin.collections.Map<String, Int> = mapOf(
    "Rabbit" to 8,
    "Wolf" to 4,
    "Rock" to 10,
    "Tree" to 10,
    "Grass" to 30)

class Simulation {
    private var amountOfTurns : Int = 0
    private lateinit var map : Map
   private val gameData = GameData()

    fun startSimulation() {
        initSimulation()
        printGameInfo()
        askUserResponse()
    }

    private fun nextTurn() {
        amountOfTurns++
        turnActions()
        plantGrass()
        println(gameData.getCurrentGameStats(map))
        checkGameState()
    }



    private fun printMap(){
        println("Текущий ход: $amountOfTurns")
        MapRenderer().renderMap(map)
    }


    private fun printGameInfo(){
        printMap()
        println("Введите $START_LOOP_SIMULATION_CODE, чтобы запустить цикл симуляции")
        println("Введите $NEXT_TURN_SIMULATION_CODE, чтобы запустить следующий ход симуляции")
        println("Введите $STOP_SIMULATION_CODE, чтобы остановть симуляцию")
        println()


    }

    private fun startGameLoop(){
        while (true){
            nextTurn()
            printGameInfo()
            Thread.sleep(500)
            checkGameState()
        }
    }

    private fun getValidUserInput(): String {
        val validValues = listOf("1","2","3")
        while (true){
            val userInput = readln()
            if (userInput in validValues){
                return userInput
            }
            println("Введите корректное значение! $START_LOOP_SIMULATION_CODE/$NEXT_TURN_SIMULATION_CODE/$STOP_SIMULATION_CODE")
        }
    }

    private fun askUserResponse() {
        val userInput = getValidUserInput()
        when(userInput){
            "1" -> startGameLoop()
            "2" -> {
                    nextTurn()
                    printGameInfo()
                    askUserResponse()
            }
            "3" -> pauseSimulation()
        }
    }

    private fun pauseSimulation() {
        println("Симуляция приостановлена")
        println("Введите $START_LOOP_SIMULATION_CODE, чтобы запустить цикл симуляции")
        println("Введите $NEXT_TURN_SIMULATION_CODE, чтобы запустить следующий ход симуляции")
        askUserResponse()
    }

    private fun initSimulation(){
        map = Map()
        map.createMap()
        initAllTypesOfEntities(map)
        println(gameData.getCurrentGameStats(map))
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

    private fun checkGameState() {
        val currentGameData = gameData.getCurrentGameStats(map)
        if (currentGameData["Rabbit"] == 0){
            println("Симуляция завершена. Все зайцы вымерли :(")
            println()
            amountOfTurns =0
            startSimulation()
        }
        else if (currentGameData["Wolf"] == 0){
            println("Симуляция завершена. Все волки вымерли :(")
            println()
            amountOfTurns =0
            startSimulation()

        }
    }


    private fun plantGrass(){
        val randomNumber = Random.nextInt(1, 3)
        if (randomNumber == 1){
            map.createEntityOnRandomCoordinates(entities.Grass())
        }
    }

    private fun createEntity(entity: Entity){
        val amountOfEntities  = AMOUNT_OF_ENTITIES_FOR_SPAWN[entity.entityName]
        if(amountOfEntities is Int){
            for (i in 1..amountOfEntities) {
                map.createEntityOnRandomCoordinates(entity)
            }
        }
    }

    private fun turnActions() {
        val currentMap = map.getMap().toMap()
        val iterator = currentMap.entries.iterator()
        while (iterator.hasNext()) {
            val (coordinate, entity) = iterator.next()
            if (entity is Herbivore) {
                map = entity.makeMove(coordinate, map)
            }
            else if (entity is Predator) {
                map = entity.makeMove(coordinate, map)
            }
        }
    }
}