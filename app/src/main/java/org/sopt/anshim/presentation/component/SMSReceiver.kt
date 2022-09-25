package org.sopt.anshim.presentation.component

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.telephony.SmsMessage

class SMSReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if ("android.provider.Telephony.SMS_RECEIVED" == intent?.action) {
            val bundle = intent.extras
            val message = parseMessage(bundle)

            if (message.isEmpty()) return
            message[0]?.let {
                val phoneNumber = it.originatingAddress!!
                val contents = it.messageBody.toString()
                sendToActivity(context, phoneNumber, contents)
            }
        }
    }

    private fun sendToActivity(context: Context?, phoneNumber: String, content: String) {
        val intent = Intent(context, BroadCastActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP)
        intent.putExtra(ARG_PHONE_NUMBER, phoneNumber)
        intent.putExtra(ARG_CONTENT, content)
        context?.startActivity(intent)
    }

    private fun parseMessage(bundle: Bundle?): Array<SmsMessage?> {
        val objs = bundle?.get("pdus") as Array<*>
        val messages = arrayOfNulls<SmsMessage>(objs.size)

        for (i in messages.indices) {
            val format = bundle.getString("format")
            messages[i] = SmsMessage.createFromPdu(objs[i] as ByteArray, format)
        }
        return messages
    }

    companion object {
        const val ARG_PHONE_NUMBER = "phoneNumber"
        const val ARG_CONTENT = "content"
    }
}