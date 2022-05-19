package org.sopt.anshim.presentation.lotto

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonParser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.sopt.anshim.presentation.types.LottoNumViewType
import org.sopt.anshim.util.safeLet
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*
import kotlin.random.Random

class LottoViewModel : ViewModel() {
    private val lottoNums = LottoNumViewType.values()

    private var _generatedLottoNums = MutableLiveData<List<LottoNumViewType>>()
    private var _winningLottoNums = MutableLiveData<List<Int>>()
    private var _resultMsg = MutableLiveData<String>()

    val round = "1010"
    val generatedLottoNums get() = _generatedLottoNums
    val winningLottoNums get() = _winningLottoNums
    val resultMsg get() = _resultMsg

    fun drawLotto() {
        generateLottoNums()
        viewModelScope.launch {
            safeLet(getWinningLottoNums(),
                generatedLottoNums.value?.map { it.ordinal }) { winningNums, generatedNums ->
                setWinningResult(winningNums, generatedNums)
            }
        }
    }

    private fun generateLottoNums() {
        val result = arrayListOf<LottoNumViewType>()
        val source = IntArray(45) { it + 1 }
        val seed =
            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.KOREA).format(Date()).hashCode()
                .toLong()
        val random = Random(seed)
        source.shuffle(random)
        source.slice(0..5).forEach { num ->
            result.add(lottoNums[num])
        }
        result.sort()

        _generatedLottoNums.value = result
    }

    private suspend fun getWinningLottoNums(): List<Int>? {
        val url = "https://dhlottery.co.kr/common.do?method=getLottoNumber&drwNo=$round"
        val lottoNums = ArrayList<Int>()

        return withContext(Dispatchers.IO) {
            try {
                val response = URL(url).readText()
                val jsonObject = JsonParser.parseString(response).asJsonObject
                val returnValue = jsonObject.get("returnValue").asString

                if (returnValue == "success") {
                    for (i in 1..6) {
                        val lottoNum = jsonObject.get("drwtNo$i").asInt
                        lottoNums.add(lottoNum)
                    }
                    val bonusNumber = jsonObject.get("bnusNo").asInt
                    lottoNums.add(bonusNumber)
                }
                winningLottoNums.postValue(lottoNums)
                lottoNums
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }

    private fun setWinningResult(winningNums: List<Int>, generatedNums: List<Int>) {
        val diff = winningNums.toSet().minus(generatedNums)
        var numOfCommonNums = winningNums.size - diff.size

        val isExistBonusNum = generatedNums.contains(winningNums[6])
        if (isExistBonusNum) numOfCommonNums-- // 보너스 숫자를 제외하고 맞힌 숫자

        resultMsg.value = when (numOfCommonNums) {
            3 -> "본전은 뽑았다!! 5️⃣등 축하드려요 🤭"
            4 -> "오늘 저녁은 치킨? 🍗 4️⃣등 축하드려요!"
            5 -> {
                if (isExistBonusNum) "우와아 2️⃣등!️✌️ 축하드립니다!️" // 당첨번호 5개 숫자일치 + 보너스 숫자일치
                else "축하드립니다! 3️⃣등입니다 🏄🏻‍🏄🏻‍️🏄🏻‍️"
            }
            6 -> "당장 퇴사 갈기세요! 1️⃣등 축하드려요! 🥳🥳🎂"
            else -> "앗, 낙첨이네요 🥲 다음 기회를 노려보아요.."
        }
    }

    companion object {
        private const val TAG = "LottoViewModel"
    }
}