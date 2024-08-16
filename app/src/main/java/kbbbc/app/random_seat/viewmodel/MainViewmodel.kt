package kbbbc.app.random_seat.viewmodel

import androidx.lifecycle.ViewModel
import kbbbc.app.random_seat.util.SeatManager

class MainViewmodel: ViewModel() {
    fun getRandomSeat(): Pair<SeatManager.Position, String> {
        return SeatManager.getRandomSeat()
    }
}