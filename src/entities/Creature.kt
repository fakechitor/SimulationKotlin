package entities

import map.Map


abstract class Creature : Entity() {
    abstract var amountOfSpeed : Int
    abstract var healthPoints: Int
    abstract fun makeMove(startCoordinates: map.Coordinates, map: Map)
    abstract fun eatFood(startCoordinates: map.Coordinates, map: Map, newCoordinates: map.Coordinates)
    abstract fun decreaseHealthPointsByHunger()
}