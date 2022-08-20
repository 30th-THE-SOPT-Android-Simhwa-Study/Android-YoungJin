package org.sopt.anshim.presentation.component

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import org.sopt.anshim.R
import org.sopt.anshim.databinding.ActivityServiceBinding
import org.sopt.anshim.util.Actions.START_FOREGROUND
import org.sopt.anshim.util.Actions.STOP_FOREGROUND

class ServiceActivity : AppCompatActivity() {
    private lateinit var binding: ActivityServiceBinding
    private lateinit var musicIntent: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_service)
        musicIntent = Intent(this, MusicPlayerService::class.java)
        addListeners()
    }

    private fun addListeners() {
        binding.start.setOnClickListener {
            musicIntent.action = START_FOREGROUND
            startService(musicIntent)
        }

        binding.stop.setOnClickListener {
            musicIntent.action = STOP_FOREGROUND
            startService(musicIntent)
        }
    }
}