package org.sopt.anshim.presentation.component

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class NotiReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        var data1 = intent?.getStringExtra("data")
        Toast.makeText(context, data1, Toast.LENGTH_SHORT).show()
    }
}