package entities

abstract class Creature() : Entity() {
    abstract var amountOfSpeed : Int
    abstract var healthPoints: Int
    abstract fun makeMove()
}