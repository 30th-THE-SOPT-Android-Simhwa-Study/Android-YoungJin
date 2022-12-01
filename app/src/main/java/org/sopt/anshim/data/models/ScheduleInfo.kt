package org.sopt.anshim.data.models

import org.sopt.anshim.data.models.types.ScheduleType
import java.time.LocalDateTime

data class ScheduleInfo(
    val type: ScheduleType,
    val title: String,
    val time: LocalDateTime,
)