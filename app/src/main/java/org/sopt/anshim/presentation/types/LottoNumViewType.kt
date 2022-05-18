package org.sopt.anshim.presentation.types

import org.sopt.anshim.R

/** 로또 번호 1, 45까지를 정의하고 번호에 해당하는 컬러를 추출 (단, EMPTY의 경우 사용되지는 않지만 인덱스가 0부터 시작하는 것을 고려하여 추가함) */
enum class LottoNumViewType {
    EMPTY, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE,
    TEN, ELEVEN, TWELVE, THIRTEEN, FOURTEEN, FIFTEEN, SIXTEEN, SEVENTEEN, EIGHTEEN, NINETEEN,
    TWENTY, TWENTY_ONE, TWENTY_TWO, TWENTY_THREE, TWENTY_FOUR, TWENTY_FIVE, TWENTY_SIX, TWENTY_SEVEN, TWENTY_EIGHT, TWENTY_NINE,
    THIRTY, THIRTY_ONE, THIRTY_TWO, THIRTY_THREE, THIRTY_FOUR, THIRTY_FIVE, THIRTY_SIX, THIRTY_SEVEN, THIRTY_EIGHT, THIRTY_NINE,
    FORTY, FORTY_ONE, FORTY_TWO, FORTY_THREE, FORTY_FOUR, FORTY_FIVE;

    fun getColor(): Int {
        return when(this.ordinal) {
            in 1..9 -> R.color.green
            in 10..19 -> R.color.light_pink
            in 20..29 -> R.color.strawberry_pink
            in 30..39 -> R.color.hot_pink
            else -> R.color.purple
        }
    }
}