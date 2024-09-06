package map

import Emoji
import entities.Entity



class MapRenderer {
    fun renderMap(map : Map) {
        for (i in 1..AMOUNT_OF_ROWS){
            for (j in 1..AMOUNT_OF_COLUMNS){
                val name = map.getCellStatus(Coordinates(i, j))
                var emoji = "\uD83D\uDFEB"
                if (name is Entity){
                    emoji = Emoji().getEmoji(name.entityName).toString()
                }
                print(emoji)
            }
            println()
        }
    }
}