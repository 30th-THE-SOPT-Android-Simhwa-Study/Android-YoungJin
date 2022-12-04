package org.sopt.anshim.presentation.calendar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.anshim.data.models.ScheduleInfo
import org.sopt.anshim.data.models.types.ScheduleType
import org.sopt.anshim.presentation.ui.theme.*
import java.time.LocalDateTime

class CalendarActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnshimTheme {
//                ScheduleList()
            }
        }
    }
}

@Composable
fun ScheduleList(
    schedules: List<ScheduleInfo>,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),
        verticalArrangement = Arrangement.spacedBy(10.dp)
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
fun SchedulePreview() {
    AnshimTheme {
        val schedules = listOf(
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
        ScheduleList(schedules)
//        ScheduleItem(schedules[0])
    }
}
