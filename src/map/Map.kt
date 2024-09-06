package map

import entities.Herbivore
import kotlin.random.Random

const val AMOUNT_OF_COLUMNS = 15
const val AMOUNT_OF_ROWS = 10

class Map {
    private var map : MutableMap<Coordinates, Any> = mutableMapOf()

    fun createMap() : MutableMap<Coordinates, Any> {
        for (i in 1..AMOUNT_OF_COLUMNS) {
            for (j in 1..AMOUNT_OF_ROWS) {
                map[Coordinates(i, j)] = ""
            }
        }
        return map
    }

    fun createEntityOnRandomCoordinates(entity : Any){
        while(true){
            val randomRow = Random.nextInt(1, AMOUNT_OF_ROWS)
            val randomColumn = Random.nextInt(1, AMOUNT_OF_COLUMNS)
            if (checkIfCellIsOccupied(randomRow, randomColumn)) {
                setEntity(entity, Coordinates(randomRow, randomColumn))
                break
            }
        }
    }

    private fun checkIfCellIsOccupied(col : Int, row : Int) : Boolean {
        return getCellStatus(Coordinates(row,col)) == ""
    }

    private fun setEntity(entity: Any, coordinates : Coordinates){
        map[coordinates] = entity
    }

    fun getCellStatus(coordinates: Coordinates) : Any? {
        return map[coordinates]
    }
}