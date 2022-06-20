package org.sopt.anshim.presentation.sign.screens

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import org.sopt.anshim.R
import org.sopt.anshim.databinding.ActivitySignInUsernameBinding
import org.sopt.anshim.presentation.github.PushEventActivity
import org.sopt.anshim.presentation.sign.viewmodels.SignViewModel

class SignInUsernameActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInUsernameBinding
    private val viewModel: SignViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_in_username)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this@SignInUsernameActivity

        addListeners()
    }

    private fun addListeners() {
        binding.signIn.setOnClickListener {
            Intent(this, PushEventActivity::class.java).run {
                putExtra(ARG_USER_NAME, viewModel.userName.value)
                startActivity(this)
            }
        }
    }

    companion object {
        private const val TAG = "SignInUsernameActivity"
        private const val ARG_USER_NAME = "userName"
    }
}