package map

import Emoji



class MapRenderer {
    private val emojiRenderer = Emoji()

    fun renderMap(map : Map) {
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