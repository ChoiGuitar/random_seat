package kbbbc.app.random_seat.util

object IsPasswordCorrect {
    operator fun invoke(password: String): Boolean {
        return password.equals("7552")
    }
}