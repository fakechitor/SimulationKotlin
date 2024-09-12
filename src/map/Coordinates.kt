package map

class Coordinates(row : Int, col : Int) {
    private var columnNumber = col
    private var rowNumber = row

    fun getCoordinates() : Pair<Int, Int> {
        return Pair(rowNumber,columnNumber)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Coordinates

        if (columnNumber != other.columnNumber) return false
        if (rowNumber != other.rowNumber) return false

        return true
    }

    override fun hashCode(): Int {
        var result = columnNumber
        result = 31 * result + rowNumber
        return result
    }


}