package org.sopt.anshim.presentation.component

import android.Manifest
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import org.sopt.anshim.databinding.ActivityBroadcastBinding

class BroadCastActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBroadcastBinding
    private lateinit var br: SMSReceiver
    private lateinit var filter: IntentFilter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBroadcastBinding.inflate(layoutInflater)
        setContentView(binding.root)

        requirePerms()
        processedIntent(intent)
        addListeners()
    }

    override fun onStart() {
        super.onStart()
        br = SMSReceiver()
        filter = IntentFilter().apply {
            addAction("org.sopt.anshim")
        }
        registerReceiver(br, filter)
        super.onResume()
    }

    override fun onStop() {
        unregisterReceiver(br)
        super.onStop()
    }

    private fun processedIntent(intent: Intent?) {
        intent?.let {
            binding.phone.setText(it.getStringExtra(ARG_PHONE_NUMBER))
            binding.content.setText(it.getStringExtra(ARG_CONTENT))
        }
    }

    private fun addListeners() {
        binding.btnSend.setOnClickListener {
            // TODO implement
        }
    }

    // 앱이 종료되지 않은 상태에서 메시지를 받을경우 Receiver 에서 startActivity에 의해 onNewIntent가 실행됨.
    override fun onNewIntent(intent: Intent?) {
        processedIntent(intent)
        super.onNewIntent(intent)
    }

    private fun requirePerms() {
        val permissions = arrayOf(Manifest.permission.RECEIVE_SMS)
        val permissionCheck =
            ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS)
        if (permissionCheck == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, permissions, 1)
        }
    }

    companion object {
        const val ARG_PHONE_NUMBER = "phoneNumber"
        const val ARG_CONTENT = "content"
    }
}