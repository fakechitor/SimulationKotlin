class Emoji {
    fun getEmoji(entityName: String): String? {
        when(entityName) {
            "Tree" -> return "\uD83C\uDF33"
            "Rock" -> return "\uD83E\uDEA8"
            "Grass" -> return "\uD83C\uDF3F"
            "Rabbit" -> return "\uD83D\uDC07"
            "Wolf" -> return "\uD83D\uDC3A"
        }
        return ""
    }
}
