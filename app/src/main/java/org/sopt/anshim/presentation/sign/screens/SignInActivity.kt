package org.sopt.anshim.presentation.sign.screens

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import org.sopt.anshim.R
import org.sopt.anshim.databinding.ActivitySignInBinding
import org.sopt.anshim.presentation.home.screens.HomeActivity
import org.sopt.anshim.presentation.sign.viewmodels.SignViewModel

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding
    private val viewModel: SignViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_in)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this@SignInActivity

        addObservers()
    }

    private fun addObservers() {
        viewModel.getCompleteSignIn().observe(this) { isCompleted ->
            if (!isCompleted) return@observe
            Intent(this, HomeActivity::class.java).run {
                putExtra(ARG_USER_INFO, viewModel.getUserInfo())
                startActivity(this)
                finish()
            }
        }
    }

    companion object {
        private const val ARG_USER_INFO = "userInfo"
    }
}