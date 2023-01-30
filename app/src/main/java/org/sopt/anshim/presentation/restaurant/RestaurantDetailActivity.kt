package org.sopt.anshim.presentation.restaurant

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import org.sopt.anshim.R
import org.sopt.anshim.databinding.ActivityRestuarantDetailBinding

class RestaurantDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRestuarantDetailBinding
    private val viewModel: RestaurantViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_restuarant_detail)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this@RestaurantDetailActivity
    }
}