package kbbbc.app.random_seat.`interface`

import android.widget.TextView

interface OnPasswordListener {
    fun onDialogResult(password: String, textView: TextView)
}