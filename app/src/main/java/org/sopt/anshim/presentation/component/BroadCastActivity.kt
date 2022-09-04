package org.sopt.anshim.presentation.component

import android.Manifest
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Bundle
import android.telephony.SmsManager
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
            val verificationCode = it.getStringExtra(ARG_CONTENT)?.replace("[^\\d]".toRegex(), "")
            binding.content.setText(verificationCode)
        }
    }

    private fun addListeners() {
        binding.btnSend.setOnClickListener {
            try {
                val smsManager = SmsManager.getDefault()
                smsManager.sendTextMessage(
                    binding.phone.toString(),
                    null,
                    binding.content.toString(),
                    null,
                    null)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    // 앱이 종료되지 않은 상태에서 메시지를 받을경우 Receiver 에서 startActivity에 의해 onNewIntent가 실행됨.
    override fun onNewIntent(intent: Intent?) {
        processedIntent(intent)
        super.onNewIntent(intent)
    }

    private fun requirePerms() {
        val permissions = arrayOf(Manifest.permission.RECEIVE_SMS, Manifest.permission.SEND_SMS)
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.RECEIVE_SMS) == PackageManager.PERMISSION_DENIED || ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_DENIED
        ) {
            ActivityCompat.requestPermissions(this, permissions, 1)
        }
    }

    companion object {
        const val ARG_PHONE_NUMBER = "phoneNumber"
        const val ARG_CONTENT = "content"
    }
}