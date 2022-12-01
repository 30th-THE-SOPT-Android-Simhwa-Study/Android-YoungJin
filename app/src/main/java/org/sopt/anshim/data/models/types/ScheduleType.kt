package org.sopt.anshim.data.models.types

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import org.sopt.anshim.R
import org.sopt.anshim.presentation.ui.theme.Blue500
import org.sopt.anshim.presentation.ui.theme.Green500
import org.sopt.anshim.presentation.ui.theme.Orange500
import org.sopt.anshim.presentation.ui.theme.Pink500

enum class ScheduleType(@DrawableRes val imgRes: Int, @StringRes val strRes: Int, val colorRes: Color) {
    HANG_OUT(R.drawable.ic_hang_out, R.string.calendar_schedule_hangout, Green500),
    STUDY(R.drawable.ic_study, R.string.calendar_schedule_study, Blue500),
    EXERCISE(R.drawable.ic_exercise, R.string.calendar_schedule_exercise, Pink500),
    MEETING(R.drawable.ic_meeting, R.string.calendar_schedule_meeting, Orange500)
}