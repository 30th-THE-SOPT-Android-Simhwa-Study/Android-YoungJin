package org.sopt.anshim.presentation.lotto

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.anshim.presentation.types.LottoNumViewType
import java.text.SimpleDateFormat
import java.util.*
import kotlin.random.Random

class LottoViewModel : ViewModel() {
    private val lottoNums = LottoNumViewType.values()
    private var _generatedLottoNumList = MutableLiveData<List<LottoNumViewType>>()
    val generatedLottoNumList get() = _generatedLottoNumList

    fun generateLottoNums() {
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

        _generatedLottoNumList.value = result
    }

    companion object {
        private const val TAG = "LottoViewModel"
    }
}