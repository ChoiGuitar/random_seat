package kbbbc.app.random_seat.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import kbbbc.app.random_seat.databinding.FragmentNewSeatDialogBinding

class NewSeatDialogFragment : DialogFragment() {
    private lateinit var binding: FragmentNewSeatDialogBinding

    companion object{
        private var newSeatText = ""

        fun newInstance(newSeat: String) = NewSeatDialogFragment().apply{
            newSeatText = newSeat
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewSeatDialogBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.textviewNewSeat.text = newSeatText

        binding.buttonNewSeatConfirm.setOnClickListener{
            dismiss()
        }
    }
}