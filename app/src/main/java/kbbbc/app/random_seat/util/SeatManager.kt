package kbbbc.app.random_seat.util

import android.util.Log
import kotlin.random.Random

object SeatManager {
    enum class Position{
        LEFT,
        CENTER,
        RIGHT
    }

    private val initialLeftSeatList = listOf(
        "a01", "a02", "a03",
        "b01", "b02", "b03", "b04",
        "c01", "c02", "c03", "c04", "c05",
        "d01", "d02", "d03", "d04", "d05",
        "e01", "e02", "e03", "e04", "e05",
    )

    private val initialCenterSeatList = listOf(
        "a04", "a05", "a06", "a07", "a08", "a09",
        "b05", "b06", "b07", "b08", "b09", "b10",
        "c06", "c07", "c08", "c09", "c10", "c11"
    )

    private val initialRightSeatList = listOf(
        "a10", "a11", "a12", "a13", "a14",
        "b11", "b12", "b13", "b14", "b15", "b16",
        "c12", "c13", "c14", "c15", "c16", "c17", "c18",
        "d06", "d07", "d08", "d09", "d10", "d11", "d12"
    )

    private lateinit var currentLeftSeatList: MutableList<String>
    private lateinit var currentCenterSeatList: MutableList<String>
    private lateinit var currentRightSeatList: MutableList<String>

    init {
        initializeSeatList()
    }

    fun initializeSeatList() {
        currentLeftSeatList = initialLeftSeatList.toMutableList()
        currentCenterSeatList = initialCenterSeatList.toMutableList()
        currentRightSeatList = initialRightSeatList.toMutableList()
    }

    fun getRandomSeat(): Pair<Position, String> {
        while (true) {
            if (currentLeftSeatList.size == 0 && currentCenterSeatList.size == 0 && currentRightSeatList.size == 0) {
                return Pair(Position.LEFT, "empty")
            }

            // 0.0 <= value < 1.0
            val randomPosition = Random.nextDouble()

            val selectedPosition = when {
                // left
                randomPosition < 0.25 -> {
                    if (currentLeftSeatList.size != 0) Position.LEFT else continue
                }
                // right
                randomPosition < 0.50 -> {
                    if (currentRightSeatList.size != 0) Position.RIGHT else continue
                }
                // center
                else -> {
                    if (currentCenterSeatList.size != 0) Position.CENTER else continue
                }
            }

            when(selectedPosition) {
                Position.LEFT -> {
                    val randomSeatIndex = (Random.nextDouble() * currentLeftSeatList.size.toDouble()).toInt()

                    val randomSeatId = currentLeftSeatList.get(index = randomSeatIndex)
                    currentLeftSeatList.removeAt(index = randomSeatIndex)

                    return Pair(selectedPosition, randomSeatId)
                }

                Position.RIGHT -> {
                    val randomSeatIndex = (Random.nextDouble() * currentRightSeatList.size.toDouble()).toInt()

                    val randomSeatId = currentRightSeatList.get(index = randomSeatIndex)
                    currentRightSeatList.removeAt(index = randomSeatIndex)

                    return Pair(selectedPosition, randomSeatId)
                }

                Position.CENTER -> {
                    val randomSeatIndex = (Random.nextDouble() * currentCenterSeatList.size.toDouble()).toInt()

                    val randomSeatId = currentCenterSeatList.get(index = randomSeatIndex)
                    currentCenterSeatList.removeAt(index = randomSeatIndex)

                    return Pair(selectedPosition, randomSeatId)
                }
            }
        }
    }

    fun setOwnSeat(seatId: String) {
        currentLeftSeatList.indexOf(seatId).let { index ->
            if (index != -1) {
                currentLeftSeatList.removeAt(index)
            }
        }

        currentRightSeatList.indexOf(seatId).let { index ->
            if (index != -1) {
                currentRightSeatList.removeAt(index)
            }
        }

        currentCenterSeatList.indexOf(seatId).let { index ->
            if (index != -1) {
                currentCenterSeatList.removeAt(index)
            }
        }
    }
}