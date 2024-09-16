package map

import entities.*
import kotlin.random.Random

const val AMOUNT_OF_COLUMNS = 15
const val AMOUNT_OF_ROWS = 10

class Map {
     private var map : MutableMap<Coordinates, Any> = mutableMapOf()

    fun getMap(): MutableMap<Coordinates, Any> {
        return map
    }

    fun createMap() : MutableMap<Coordinates, Any> {
        for (i in 1..AMOUNT_OF_ROWS) {
            for (j in 1..AMOUNT_OF_COLUMNS) {
                map[Coordinates(i, j)] = ""
            }
        }
        return map
    }

    fun createEntityOnRandomCoordinates(entity : Any) {
        var randomRow: Int
        var randomColumn: Int
        while(true) {
            randomRow = Random.nextInt(1, AMOUNT_OF_ROWS + 1)
            randomColumn = Random.nextInt(1, AMOUNT_OF_COLUMNS + 1)
            if (isCellEmpty(randomRow, randomColumn)) {
                setEntity(entity, Coordinates(randomRow, randomColumn))
                break
            }
        }
    }

    fun getCellStatus(coordinates: Coordinates) : Any? {
        val cellData  = map[coordinates]
        when(cellData){
            is Herbivore -> return Entities.Rabbit
            is Predator -> return Entities.Wolf
            is Rock -> return Entities.Rock
            is Tree -> return Entities.Tree
            is Grass -> return Entities.Grass
            is String -> return ""
        }
        return cellData
    }

    private fun isCellEmpty(row : Int,col : Int) : Boolean {
        return getCellStatus(Coordinates(row,col)).toString() == ""
    }

    fun setEntity(entity: Any, coordinates : Coordinates){
        map[coordinates] = entity
    }
    fun removeEntity(coordinates : Coordinates){
        map.remove(coordinates)
        map[coordinates] = ""
    }

}