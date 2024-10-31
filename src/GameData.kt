import entities.Entity

class GameData {
    private var entityData : MutableMap<String, Int> = mutableMapOf(
    "Rabbit" to 0,
    "Wolf" to 0,
    "Rock" to 0,
    "Tree" to 0,
    "Grass" to 0
)

    fun getCurrentGameStats(map : map.Map): MutableMap<String, Int> {
        updateGameStats(map)
        return entityData
    }

    private fun updateGameStats(map : map.Map){
        val newEntityData = clearEntityDataValues(entityData)
        for (cellStatus in map.map.values){
            if (cellStatus is Entity) {
                val previousAmount = newEntityData[cellStatus.entityName]
                newEntityData[cellStatus.entityName] = previousAmount!! + 1
            }
        }
        entityData = newEntityData
    }

    private fun clearEntityDataValues(entityDataMap : MutableMap<String,Int>) : MutableMap<String, Int>{
        for (key in entityDataMap.keys){
            entityDataMap[key] = 0
        }
        return entityDataMap

    }
}