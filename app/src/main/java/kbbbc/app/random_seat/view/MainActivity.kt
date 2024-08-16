package kbbbc.app.random_seat.view

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import kbbbc.app.random_seat.`interface`.OnPasswordListener
import kbbbc.app.random_seat.R
import kbbbc.app.random_seat.databinding.ActivityMainBinding
import kbbbc.app.random_seat.util.IsPasswordCorrect
import kbbbc.app.random_seat.viewmodel.MainViewmodel


class MainActivity : AppCompatActivity(), OnPasswordListener {
    private lateinit var binding: ActivityMainBinding

    private var backPressedTime: Long = 0

    private val viewModel: MainViewmodel by viewModels()

    private lateinit var seatViewMap: Map<String, TextView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        seatViewMap = mapOf(
            "a01" to binding.seatListLeft.a01,
            "a02" to binding.seatListLeft.a02,
            "a03" to binding.seatListLeft.a03,
            "a04" to binding.seatListCenter.a04,
            "a05" to binding.seatListCenter.a05,
            "a06" to binding.seatListCenter.a06,
            "a07" to binding.seatListCenter.a07,
            "a08" to binding.seatListCenter.a08,
            "a09" to binding.seatListCenter.a09,
            "a10" to binding.seatListRight.a10,
            "a11" to binding.seatListRight.a11,
            "a12" to binding.seatListRight.a12,
            "a13" to binding.seatListRight.a13,
            "a14" to binding.seatListRight.a14,
            "b01" to binding.seatListLeft.b01,
            "b02" to binding.seatListLeft.b02,
            "b03" to binding.seatListLeft.b03,
            "b04" to binding.seatListLeft.b04,
            "b05" to binding.seatListCenter.b05,
            "b06" to binding.seatListCenter.b06,
            "b07" to binding.seatListCenter.b07,
            "b08" to binding.seatListCenter.b08,
            "b09" to binding.seatListCenter.b09,
            "b10" to binding.seatListCenter.b10,
            "b11" to binding.seatListRight.b11,
            "b12" to binding.seatListRight.b12,
            "b13" to binding.seatListRight.b13,
            "b14" to binding.seatListRight.b14,
            "b15" to binding.seatListRight.b15,
            "b16" to binding.seatListRight.b16,
            "c01" to binding.seatListLeft.c01,
            "c02" to binding.seatListLeft.c02,
            "c03" to binding.seatListLeft.c03,
            "c04" to binding.seatListLeft.c04,
            "c05" to binding.seatListLeft.c05,
            "c06" to binding.seatListCenter.c06,
            "c07" to binding.seatListCenter.c07,
            "c08" to binding.seatListCenter.c08,
            "c09" to binding.seatListCenter.c09,
            "c10" to binding.seatListCenter.c10,
            "c11" to binding.seatListCenter.c11,
            "c12" to binding.seatListRight.c12,
            "c13" to binding.seatListRight.c13,
            "c14" to binding.seatListRight.c14,
            "c15" to binding.seatListRight.c15,
            "c16" to binding.seatListRight.c16,
            "c17" to binding.seatListRight.c17,
            "c18" to binding.seatListRight.c18,
            "d01" to binding.seatListLeft.d01,
            "d02" to binding.seatListLeft.d02,
            "d03" to binding.seatListLeft.d03,
            "d04" to binding.seatListLeft.d04,
            "d05" to binding.seatListLeft.d05,
            "d06" to binding.seatListRight.d06,
            "d07" to binding.seatListRight.d07,
            "d08" to binding.seatListRight.d08,
            "d09" to binding.seatListRight.d09,
            "d10" to binding.seatListRight.d10,
            "d11" to binding.seatListRight.d11,
            "d12" to binding.seatListRight.d12,
            "e01" to binding.seatListLeft.e01,
            "e02" to binding.seatListLeft.e02,
            "e03" to binding.seatListLeft.e03,
            "e04" to binding.seatListLeft.e04,
            "e05" to binding.seatListLeft.e05
        )

        setupOnClickListener()
    }

    @SuppressLint("DiscouragedApi")
    private fun setupOnClickListener() {
        binding.buttonMainDrawSeat.setOnClickListener{
            it.isEnabled = false

            val randomSeat = viewModel.getRandomSeat()

            if (randomSeat.second != "empty") {
                val resId = resources.getIdentifier(randomSeat.second, "string", packageName)

                NewSeatDialogFragment.newInstance(
                    getString(resId)
                ).show(supportFragmentManager, null)

                seatViewMap[randomSeat.second]?.let {
                    it.setBackgroundResource(R.drawable.selected_bg)
                    it.isEnabled = false
                }
            } else {
                Toast.makeText(this, "자리를 초기화해주세요.", Toast.LENGTH_SHORT).show()
            }

            it.isEnabled = true
        }

        seatViewMap.values.forEach {
            it.setOnClickListener {
                val textView = it as TextView
                ConfirmDialogFragment.newInstance(textView).show(supportFragmentManager, null)
            }
        }
    }

    private fun findStringResourceName(searchString: String): String {
        val fields = R.string::class.java.fields
        for (field in fields) {
            val resourceId = resources.getIdentifier(field.name, "string", packageName)
            if (resourceId != 0) {
                val value = resources.getString(resourceId)
                if (value == searchString) {
                    return field.name
                }
            }
        }
        return "" // 리소스를 찾지 못한 경우 null을 반환
    }

    override fun onBackPressed() {
        // 뒤로가기 버튼 두번 눌렀을 때 앱 종료하도록 하는 로직
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            super.onBackPressedDispatcher.onBackPressed()
            return
        } else {
            Toast.makeText(this@MainActivity, "앱을 종료하시려면 뒤로가기 버튼을 한번 더 누르세요", Toast.LENGTH_SHORT).show()
        }
        backPressedTime = System.currentTimeMillis()
    }

    override fun onDialogResult(password: String, textView: TextView) {
        if (IsPasswordCorrect(password)) {
            textView.setBackgroundResource(R.drawable.selected_bg)
            textView.isEnabled = false
        }
    }
}