package org.sopt.anshim.presentation.home.screens

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import org.sopt.anshim.R
import org.sopt.anshim.data.model.UserInfo
import org.sopt.anshim.databinding.ActivityHomeBinding
import org.sopt.anshim.presentation.home.viewmodels.HomeViewModel

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        binding.viewModel = viewModel

        intent.getParcelableExtra<UserInfo>(ARG_USER_INFO)?.let { user ->
            viewModel.setUserInfo(user)
        }
    }

    companion object {
        private const val ARG_USER_INFO = "userInfo"
    }
}