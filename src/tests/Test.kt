package tests

import Emoji
import Simulation
import entities.Rock
import map.Coordinates
import map.MapRenderer

fun main() {
    val game = Simulation()
    game.startSimulation()
    game.printMap()
    println(game)
}