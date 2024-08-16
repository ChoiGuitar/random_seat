package kbbbc.app.random_seat.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import kbbbc.app.random_seat.R
import kbbbc.app.random_seat.databinding.FragmentConfirmDialogBinding
import kbbbc.app.random_seat.`interface`.OnPasswordListener

class ConfirmDialogFragment : DialogFragment() {
    private lateinit var binding: FragmentConfirmDialogBinding

    private var listener: OnPasswordListener? = null

    private lateinit var _textView: TextView

    companion object{
        fun newInstance(
            textView: TextView
        ) = ConfirmDialogFragment().apply{
            _textView = textView
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = if (context is OnPasswordListener) {
            context
        } else {
            throw ClassCastException("$context must implement OnDialogResultListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentConfirmDialogBinding.inflate(inflater)
        return inflater.inflate(R.layout.fragment_confirm_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonConfirmCancel.setOnClickListener {
            safeDismiss()
        }

        binding.buttonConfirmConfirm.setOnClickListener {
            Log.e("gihong","confirm")
            val password = binding.edittextConfirmPassword.text.toString()
            listener?.onDialogResult(password, _textView)
            safeDismiss()
        }
    }

    fun safeDismiss() {
        if (lifecycle.currentState.isAtLeast(Lifecycle.State.RESUMED)) {
            dismiss()
        } else {
            dismissAllowingStateLoss()
        }
    }
}