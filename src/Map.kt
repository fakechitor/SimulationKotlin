class Map {
    private val AMOUNT_OF_COLUMNS = 20
    private val AMOUNT_OF_ROWS = 10
    private var map : MutableMap<String, String> = mutableMapOf()
    // создает карту 10х20, с координатами A..J по вертикали и 1..20 по горизонтали
    fun createMap() : MutableMap<String, String>{
        for (row in 'A'..'A' + AMOUNT_OF_ROWS) {
            for (col in 1..AMOUNT_OF_COLUMNS ) {
                map["$row$col"] = " "
            }
        }
        return map
    }
    fun getMap() : MutableMap<String, String>{
        return map
    }
}