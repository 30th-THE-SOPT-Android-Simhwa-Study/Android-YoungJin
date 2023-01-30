package org.sopt.anshim.util

import org.sopt.anshim.R
import java.time.LocalDate

object DateUtil {
    private val today = LocalDate.now().dayOfWeek.value

    fun getTodayDayOfWeek() =
        when (today) {
            1 -> DayOfWeekType.SUN
            2 -> DayOfWeekType.MON
            3 -> DayOfWeekType.TUE
            4 -> DayOfWeekType.WED
            5 -> DayOfWeekType.THU
            6 -> DayOfWeekType.FRI
            else -> DayOfWeekType.SAT
        }

    fun convertIndexToDayOfWeek(index: Int): DayOfWeekType = when (index) {
        DayOfWeekType.MON.index -> DayOfWeekType.MON
        DayOfWeekType.TUE.index -> DayOfWeekType.TUE
        DayOfWeekType.WED.index -> DayOfWeekType.WED
        DayOfWeekType.THU.index -> DayOfWeekType.THU
        DayOfWeekType.FRI.index -> DayOfWeekType.FRI
        DayOfWeekType.SAT.index -> DayOfWeekType.SAT
        else -> DayOfWeekType.SUN
    }
}

/** 요일별 문자열과 인덱스를 갖는 enum class, 서버에서 전달되는 월~일 순서에 따라 index를 부여함
 * enum 프로퍼티의 ordinal을 사용할 수도 있지만 상수값 순서 변경에 따라 ordinal이 쉽게 변하기 때문에 index 프로퍼티를 추가함 */
enum class DayOfWeekType(val strRes: Int, val index: Int) {
    MON(R.string.monday, 0),
    TUE(R.string.tuesday, 1),
    WED(R.string.wednesday, 2),
    THU(R.string.thursday, 3),
    FRI(R.string.friday, 4),
    SAT(R.string.saturday, 5),
    SUN(R.string.sunday, 6)
}