package org.sopt.anshim.presentation.lotto

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import org.sopt.anshim.R
import org.sopt.anshim.databinding.ActivityLottoBinding

class LottoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLottoBinding
    private val viewModel: LottoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_lotto)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this@LottoActivity
    }
}