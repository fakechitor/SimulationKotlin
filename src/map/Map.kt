package map

import entities.Entity
import entities.Herbivore
import kotlin.random.Random

const val AMOUNT_OF_COLUMNS = 15
const val AMOUNT_OF_ROWS = 10

class Map {
    private var map : MutableMap<Coordinates, Any> = mutableMapOf()

    fun getMap(): MutableMap<Coordinates, Any> {
        return map
    }

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

    fun getCellStatus(coordinates: Coordinates) : Any? {
        val cellData  = map[coordinates]
        if (cellData is Entity){
            return cellData.entityName
        }
        return ""
    }

    private fun checkIfCellIsOccupied(col : Int, row : Int) : Boolean {
        return getCellStatus(Coordinates(row,col)) == ""
    }

    fun setEntity(entity: Any, coordinates : Coordinates){
        map[coordinates] = entity
    }
}