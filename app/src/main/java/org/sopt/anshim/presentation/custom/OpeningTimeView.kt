package org.sopt.anshim.presentation.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import org.sopt.anshim.R
import org.sopt.anshim.databinding.ViewOpeningTimeBinding
import org.sopt.anshim.util.DateUtil.convertIndexToDayOfWeek
import org.sopt.anshim.util.DateUtil.getTodayDayOfWeek

class OpeningTimeView(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {
    private lateinit var binding: ViewOpeningTimeBinding
    private lateinit var inflater: LayoutInflater

    init {
        initView()
        initListeners()
    }

    private fun initView() {
        if (!::inflater.isInitialized)
            inflater = LayoutInflater.from(context)
        binding = ViewOpeningTimeBinding.inflate(inflater, this, true).apply {
            btnDropdown.isSelected = false
            tvNextday.visibility = GONE
        }
    }

    private fun initListeners() {
        binding.root.setOnClickListener {
            controlViewAttr()
        }
    }

    private fun controlViewAttr() {
        with(binding) {
            btnDropdown.isSelected = !btnDropdown.isSelected
            tvNextday.visibility = if (btnDropdown.isSelected) View.VISIBLE else View.GONE
            tvToday.typeface =
                resources.getFont(if (btnDropdown.isSelected) R.font.suit_b else R.font.suit_m)
        }
    }

    fun setText(timeList: List<String>?) {
        if (timeList == null || timeList.isEmpty()) return
        val today = getTodayDayOfWeek()

        var nextDayStr = ""
        for (i in timeList.indices) {
            val day = (today.index + i) % timeList.size
            val dayString =
                "${context.getString(convertIndexToDayOfWeek(day).strRes)} ${timeList[day]}"
            when (i) {
                0 -> binding.tvToday.text = dayString
                timeList.size - 1 -> nextDayStr += dayString
                else -> nextDayStr += "$dayString\n"
            }
        }

        binding.tvNextday.text = nextDayStr
    }
}
