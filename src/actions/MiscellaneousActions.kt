package actions
import NEXT_TURN_SIMULATION_CODE
import START_LOOP_SIMULATION_CODE
import STOP_SIMULATION_CODE
import entities.Grass
import map.Map
import java.util.*
import kotlin.random.Random

class MiscellaneousActions : Actions() {
    private val scanner = Scanner(System.`in`)

    fun getValidUserInput(): String {
        val validValues = listOf(
            START_LOOP_SIMULATION_CODE.toString(),
            NEXT_TURN_SIMULATION_CODE.toString(),
            STOP_SIMULATION_CODE.toString()
        )
        while (true) {
            val userInput = scanner.nextLine()
            if (userInput in validValues) {
                return userInput
            }
            println("Введите корректное значение! $START_LOOP_SIMULATION_CODE/$NEXT_TURN_SIMULATION_CODE/$STOP_SIMULATION_CODE")
        }
    }

    fun plantGrass(map: Map) {
        val randomNumber = Random.nextInt(1, 3)
        if (randomNumber == 1) {
            map.createEntityOnRandomCoordinates(Grass())
        }
    }
}
