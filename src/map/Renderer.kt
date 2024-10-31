package map

import Emoji
import NEXT_TURN_SIMULATION_CODE
import START_LOOP_SIMULATION_CODE
import STOP_SIMULATION_CODE


class Renderer {
    private val emojiRenderer = Emoji()

    fun printGameInfo(flag : Boolean, amount: Int, map: Map) {
        printMap(amount, map)
        if (flag){
            println("Введите $START_LOOP_SIMULATION_CODE, чтобы запустить цикл симуляции")
            println("Введите $NEXT_TURN_SIMULATION_CODE, чтобы запустить следующий ход симуляции")
            println("Введите $STOP_SIMULATION_CODE, чтобы остановть симуляцию")
        }
        else{
            println("Введите $STOP_SIMULATION_CODE, чтобы остановть симуляцию")
        }
        println()
    }

    private fun printMap(amount : Int, map : Map){
        println("Текущий ход: $amount")
        renderMap(map)
    }

    private fun renderMap(map : Map) {
        for (i in 1..AMOUNT_OF_ROWS){
            for (j in 1..AMOUNT_OF_COLUMNS){
                val cellStatus = map.getCellStatus(Coordinates(i, j))
                var emoji = "\uD83D\uDFEB"
                if (cellStatus != null && cellStatus != ""){
                    emoji = emojiRenderer.getEmoji(cellStatus.toString()) ?: emoji
                }
                print(emoji)
            }
            println()
        }
    }
}