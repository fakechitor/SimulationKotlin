package entities

private const val PREDATOR_SPEED = 3
private const val PREDATOR_HEALTH_POINTS = 16

class Predator(name : String) : Creature() {
    override var amountOfSpeed = PREDATOR_SPEED
    override var healthPoints  = PREDATOR_HEALTH_POINTS
    override val entityName = name
    override fun makeMove() {

    }
}