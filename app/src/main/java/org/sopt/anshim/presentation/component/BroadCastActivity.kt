package org.sopt.anshim.presentation.component

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.telephony.SmsManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import org.sopt.anshim.R
import org.sopt.anshim.databinding.ActivityBroadcastBinding
import org.sopt.anshim.util.ext.showToast

class BroadCastActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBroadcastBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBroadcastBinding.inflate(layoutInflater)
        setContentView(binding.root)

        requirePerms()
        processedIntent(intent)
        addListeners()
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
                    binding.phone.text.toString(),
                    null,
                    binding.content.text.toString(),
                    null,
                    null)

                showToast(getString(R.string.send_msg_toast))
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