package map

import Emoji
import entities.Entities



class MapRenderer {
    fun renderMap(map : Map) {
        for (i in 1..AMOUNT_OF_ROWS){
            for (j in 1..AMOUNT_OF_COLUMNS){
                val name = map.getCellStatus(Coordinates(i, j))
                var emoji = "\uD83D\uDFEB"
                if (name != ""){
                    emoji = Emoji().getEmoji(name.toString()).toString()
                }
                print(emoji)
            }
            println()
        }
    }
}