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
    fun setMap(newMap : MutableMap<Coordinates, Any>) {
        map = newMap
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
        var randomRow : Int
        var randomColumn : Int
        while(true){
            randomRow = Random.nextInt(1, AMOUNT_OF_ROWS)
            randomColumn = Random.nextInt(1, AMOUNT_OF_COLUMNS)
            if (isCellEmpty(randomRow, randomColumn)) {
                setEntity(entity, Coordinates(randomRow, randomColumn))
                break
            }
        }
    }

    fun getCellStatus(coordinates: Coordinates) : Any? {
        val cellData  = map[coordinates]
        when(cellData){
            is Herbivore -> return Entities.Rabbit.toString()
            is Predator -> return Entities.Wolf.toString()
            is Rock -> return Entities.Rock.toString()
            is Tree -> return Entities.Tree.toString()
            is Grass -> return Entities.Grass.toString()
            is String -> return ""
        }
        return cellData
    }

    private fun isCellEmpty(row : Int,col : Int) : Boolean {
        return getCellStatus(Coordinates(row,col)) == ""
    }

    fun setEntity(entity: Any, coordinates : Coordinates){
        map[coordinates] = entity
    }
}