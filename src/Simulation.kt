import actions.InitActions
import actions.MiscellaneousActions
import actions.TurnActions
import map.Map
import map.Renderer
import java.util.concurrent.atomic.AtomicBoolean

// Codes for process user input
const val START_LOOP_SIMULATION_CODE =  1
const val NEXT_TURN_SIMULATION_CODE = 2
const val STOP_SIMULATION_CODE = 3

class Simulation {
    private var amountOfTurns : Int = 1
    private lateinit var map : Map
    private val initActions = InitActions()
    private val turnActions = TurnActions()
    private val miscellaneousActions = MiscellaneousActions()
    private val gameData = GameData()
    private val renderer = Renderer()
    private var printFullGameInfoFlag = true

    fun startSimulation() {
        printFullGameInfoFlag = true
        initSimulation()
        renderer.printGameInfo(printFullGameInfoFlag, amountOfTurns, map)
        askUserResponse()
    }

    private fun initSimulation(){
        map = initActions.createSimulationMap()
    }

    private fun nextTurn() {
        amountOfTurns++
        turnActions.turnActions(map)
        miscellaneousActions.plantGrass(map)
        checkGameState()
    }


    private fun startGameLoop(){
        val running = AtomicBoolean(true)
        val gameLoopThread = Thread {
            while (running.get()) {
                nextTurn()
                renderer.printGameInfo(printFullGameInfoFlag, amountOfTurns, map)
                checkGameState()
                Thread.sleep(50)
            }
            pauseSimulation()
        }
        printFullGameInfoFlag = false
        gameLoopThread.start()

        while (running.get()) {
            val input = readLine()
            if (input == "3") {
                running.set(false)
            }
        }
        gameLoopThread.join()
        printFullGameInfoFlag = true
        askUserResponse()
    }


    private fun pauseSimulation() {
        printFullGameInfoFlag = true
        println("Симуляция приостановлена")
        println("Введите $START_LOOP_SIMULATION_CODE, чтобы запустить цикл симуляции")
        println("Введите $NEXT_TURN_SIMULATION_CODE, чтобы запустить следующий ход симуляции")
        askUserResponse()
    }

    private fun askUserResponse() {
        val userInput = miscellaneousActions.getValidUserInput()
        when(userInput){
            "1" -> startGameLoop()
            "2" -> {
                nextTurn()
                renderer.printGameInfo(printFullGameInfoFlag, amountOfTurns, map)
                askUserResponse()
            }
            "3" -> pauseSimulation()
        }
    }

    private fun checkGameState() {
        val currentGameData = gameData.getCurrentGameStats(map)
        if (currentGameData["Rabbit"] == 0) {
            println("Симуляция завершена. Все зайцы вымерли :(")
            println()
            println()
            amountOfTurns = 1
            println("Создана новая карта для симуляции:")
            startSimulation()
        } else if (currentGameData["Wolf"] == 0) {
            println("Симуляция завершена. Все волки вымерли :(")
            println()
            println()
            amountOfTurns = 1
            println("Создана новая карта для симуляции:")
            startSimulation()
        }
    }
}