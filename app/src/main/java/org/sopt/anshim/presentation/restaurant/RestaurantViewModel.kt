package org.sopt.anshim.presentation.restaurant

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.anshim.domain.models.Restaurant

class RestaurantViewModel : ViewModel() {
    private val _restaurantInfo = MutableLiveData<Restaurant>()
    val restaurantInfo: LiveData<Restaurant> get() = _restaurantInfo

    init {
        fetchRestaurantInfo()
    }

    private fun fetchRestaurantInfo() {
        _restaurantInfo.value = Restaurant(1,
            "샐러디 태릉입구점",
            4.5f,
            true,
            "서울 노원구 화랑로 421 104호",
            listOf("10:00 - 20:10",
                "10:00 - 20:10",
                "10:00 - 20:10",
                "10:00 - 20:10",
                "10:00 - 20:10",
                "10:00 - 15:00",
                "정기 휴무"),
            "070-4790-2894")
    }
}