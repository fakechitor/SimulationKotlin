package entities

private const val HERBIVORE_SPEED = 2
private const val HERBIVORE_HEALTH_POINTS = 10

class Herbivore(name : String) : Creature() {
    override var amountOfSpeed = HERBIVORE_SPEED
    override var healthPoints  = HERBIVORE_HEALTH_POINTS
    override val entityName = name
    override fun makeMove() {
        
    }

}