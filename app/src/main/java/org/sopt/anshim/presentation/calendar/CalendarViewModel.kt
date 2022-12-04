package org.sopt.anshim.presentation.calendar

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.anshim.data.models.ScheduleInfo
import org.sopt.anshim.data.models.types.ScheduleType
import java.time.LocalDate
import java.time.LocalDateTime

class CalendarViewModel : ViewModel() {
    private lateinit var now: LocalDate
    private val _year = MutableLiveData<Int>()
    val year: LiveData<Int> get() = _year
    private val _month = MutableLiveData<Int>()
    val month: LiveData<Int> get() = _month
    private val _dayOfMonth = MutableLiveData<Int>()
    val dayOfMonth: LiveData<Int> get() = _dayOfMonth
    private val _dayOfWeek = MutableLiveData<Int>()
    val dayOfWeek: LiveData<Int> get() = _dayOfWeek
    private val _dates = MutableLiveData<Array<LocalDate?>>(Array(42) {null})
    val dates: LiveData<Array<LocalDate?>> get() = _dates
    private val _schedules = MutableLiveData<List<ScheduleInfo>>()
    val schedules: LiveData<List<ScheduleInfo>> get() = _schedules

    init {
        fetchScheduleList()
    }

    private fun fetchScheduleList() {
        _schedules.value = listOf(
            ScheduleInfo(ScheduleType.MEETING,
                "헬푸미 회의",
                LocalDateTime.of(2022, 9, 18, 23, 0)),

            ScheduleInfo(ScheduleType.EXERCISE,
                "새벽 산책",
                LocalDateTime.of(2022, 10, 13, 6, 0)),

            ScheduleInfo(ScheduleType.EXERCISE,
                "밤산책",
                LocalDateTime.of(2022, 10, 28, 6, 0)),

            ScheduleInfo(ScheduleType.EXERCISE,
                "새벽 산책",
                LocalDateTime.of(2022, 11, 3, 6, 0)),
            ScheduleInfo(ScheduleType.HANG_OUT,
                "안심이랑 찜질방가기",
                LocalDateTime.of(2022, 11, 3, 15, 0)),

            ScheduleInfo(ScheduleType.EXERCISE,
                "새벽 산책",
                LocalDateTime.of(2022, 11, 8, 6, 0)),
            ScheduleInfo(ScheduleType.EXERCISE,
                "벤치프레스 30회 5세트",
                LocalDateTime.of(2022, 11, 8, 7, 0)),
            ScheduleInfo(ScheduleType.STUDY,
                "멀티모듈이랑 맞짱뜨기",
                LocalDateTime.of(2022, 11, 8, 15, 0)),

            ScheduleInfo(ScheduleType.EXERCISE,
                "데드리프트 30회 5세트",
                LocalDateTime.of(2022, 11, 25, 7, 0)),
            ScheduleInfo(ScheduleType.EXERCISE,
                "벤치프레스 30회 5세트",
                LocalDateTime.of(2022, 11, 25, 8, 0)),
            ScheduleInfo(ScheduleType.STUDY,
                "토스 자소서 쓰기",
                LocalDateTime.of(2022, 11, 25, 16, 0)),
            ScheduleInfo(ScheduleType.MEETING,
                "헬푸미 회의",
                LocalDateTime.of(2022, 11, 25, 20, 0)),
            ScheduleInfo(ScheduleType.STUDY,
                "심화스터디",
                LocalDateTime.of(2022, 11, 25, 22, 0)),

            ScheduleInfo(ScheduleType.EXERCISE,
                "풋살하기",
                LocalDateTime.of(2022, 12, 8, 15, 0)),
            ScheduleInfo(ScheduleType.STUDY,
                "코테 뿌시기",
                LocalDateTime.of(2022, 12, 8, 20, 0)),

            ScheduleInfo(ScheduleType.HANG_OUT,
                "제주도 여행",
                LocalDateTime.of(2022, 12, 18, 15, 0)),

            ScheduleInfo(ScheduleType.STUDY,
                "밀린 강의 청산",
                LocalDateTime.of(2022, 12, 27, 20, 0)),
        )
    }

    // TODO 월 값을 인자로 받아서 dates 배열 만들기
    fun fetchCurrentMonthInfo() {
        now = LocalDate.now()
        _year.value = now.year
        _month.value = now.month.value
        _dayOfMonth.value = now.dayOfMonth
        _dayOfWeek.value = now.dayOfWeek.value

        val firstIdx = now.withDayOfMonth(1).dayOfWeek.value % 7
        val lastDay = now.withDayOfMonth(now.lengthOfMonth()).dayOfMonth
        var day = 1
        for (idx in firstIdx until firstIdx + lastDay) {
            _dates.value!![idx] = LocalDate.of(year.value!!, month.value!!, day++)
        }
    }
}