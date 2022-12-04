package org.sopt.anshim.presentation.calendar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.anshim.data.models.ScheduleInfo
import org.sopt.anshim.presentation.ui.theme.*
import org.sopt.anshim.util.safeLet
import java.time.LocalDate

class CalendarActivity : ComponentActivity() {
    private val viewModel: CalendarViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnshimTheme {
                viewModel.fetchCurrentMonthInfo()
                // 추후 서버 통신을 고려한다면 dates는 항시 보여주되, schedules 존재 여부에 따라 뷰를 그려주면 될 듯
                safeLet(viewModel.dates.value, viewModel.schedules.value) { date, schedules ->
                    CalenderView(date, schedules)
                }
            }
        }
    }
}

/** 요일 뷰 */
@Composable
fun DayView(day: String, modifier: Modifier) {
    Text(
        modifier = modifier,
        text = day,
        color = Gray300,
        fontSize = 12.sp,
        fontFamily = Gmarket,
        fontWeight = FontWeight.Medium,
        textAlign = TextAlign.Center
    )
}

/** 월 - 일요일까지의 모든 요일 뷰 */
@Composable
fun DaysView() {
    Row(modifier = Modifier.fillMaxWidth()) {
        val modifier = Modifier.weight(1.0f)
        val days = listOf("Sun", "Mon", "Tue", "Wen", "Thu", "Fri", "Sat")
        days.forEach { day ->
            DayView(day = day, modifier = modifier)
        }
    }
}

/** 날짜 뷰 */
@Composable
fun DateView(date: LocalDate?, modifier: Modifier) {
    Text(
        modifier = modifier,
        text = date?.dayOfMonth?.toString() ?: "",
        color = Gray700,
        fontSize = 14.sp,
        fontFamily = Gmarket,
        fontWeight = FontWeight.Medium,
        textAlign = TextAlign.Center)
}

/** 1~28,29,30,31까지의 날짜 뷰 */
@Composable
fun DatesView(dates: Array<LocalDate?>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        val modifier = Modifier
            .weight(1.0f)
            .padding(vertical = 20.dp)
        Row(horizontalArrangement = Arrangement.SpaceAround, modifier = Modifier.fillMaxWidth()) {
            for (idx in 0..6) {
                DateView(date = dates[idx], modifier)
            }
        }
        Row(horizontalArrangement = Arrangement.SpaceAround, modifier = Modifier.fillMaxWidth()) {
            for (idx in 7..13) {
                DateView(date = dates[idx], modifier)
            }
        }
        Row(horizontalArrangement = Arrangement.SpaceAround, modifier = Modifier.fillMaxWidth()) {
            for (idx in 14..20) {
                DateView(date = dates[idx], modifier)
            }
        }
        Row(horizontalArrangement = Arrangement.SpaceAround, modifier = Modifier.fillMaxWidth()) {
            for (idx in 21..27) {
                DateView(date = dates[idx], modifier)
            }
        }
        Row(horizontalArrangement = Arrangement.SpaceAround, modifier = Modifier.fillMaxWidth()) {
            for (idx in 28..34) {
                DateView(date = dates[idx], modifier)
            }
        }
        if (dates[35] != null) {
            Row(horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.fillMaxWidth()) {
                for (idx in 35..41) {
                    DateView(date = dates[idx], modifier)
                }
            }
        }
    }
}

@Composable
fun CalenderView(dates: Array<LocalDate?>, schedules: List<ScheduleInfo>) {
    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth(),
        horizontalAlignment = CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "December 2022",
            color = Gray700,
            fontSize = 18.sp,
            fontFamily = Gmarket,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
        DaysView()
        Spacer(modifier = Modifier.height(6.dp))
        Divider(color = Gray200, modifier = Modifier.fillMaxWidth())
        DatesView(dates)
        Divider(color = Gray200, modifier = Modifier.fillMaxWidth())
        ScheduleListView(schedules)
    }
}

@Composable
fun ScheduleListView(
    schedules: List<ScheduleInfo>,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(vertical = 20.dp, horizontal = 16.dp)
    ) {
        itemsIndexed(items = schedules) { _, schedule ->
            ScheduleItem(schedule = schedule)
        }
    }
}

@Composable
fun ScheduleItem(schedule: ScheduleInfo) {
    Row(verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .border(width = 1.dp, color = Gray200, shape = RoundedCornerShape(12.dp))
            .padding(horizontal = 16.dp, vertical = 12.dp)
            .fillMaxWidth())
    {
        Image(painterResource(id = schedule.type.imgRes), contentDescription = null)

        val timeStr = "${if (schedule.time.hour in 0..11) "오전" else "오후"} ${schedule.time.hour}시"

        Spacer(modifier = Modifier.width(12.dp))

        Column() {
            Text(text = stringResource(id = schedule.type.strRes),
                color = schedule.type.colorRes,
                fontSize = 10.sp,
                fontFamily = Suit,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(text = schedule.title, color = Gray700, fontSize = 14.sp, fontFamily = Suit,
                fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = timeStr, color = Gray500, fontSize = 10.sp, fontFamily = Suit,
                fontWeight = FontWeight.Medium)
        }
    }
}

@Composable
@Preview
fun CalendarPreview() {
    AnshimTheme {
//        CalenderView()
    }
}

@Composable
@Preview
fun SchedulePreview() {
    AnshimTheme {
//        ScheduleList(schedules)
    }
}
