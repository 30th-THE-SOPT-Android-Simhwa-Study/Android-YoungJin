package org.sopt.anshim.presentation.component

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.PendingIntent.*
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import org.sopt.anshim.R
import org.sopt.anshim.util.Actions.MAIN
import org.sopt.anshim.util.Actions.NEXT
import org.sopt.anshim.util.Actions.PLAY
import org.sopt.anshim.util.Actions.PREV

object MusicNotification {
    private const val CHANNEL_ID = "MusicNoti"

    @RequiresApi(Build.VERSION_CODES.S)
    @SuppressLint("UnspecifiedImmutableFlag")
    fun createNotification(context: Context, title: String?): Notification {
        val notificationIntent = Intent(context, ServiceActivity::class.java)
        notificationIntent.action = MAIN
        notificationIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or
                Intent.FLAG_ACTIVITY_CLEAR_TASK

        val pendingIntent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            getActivity(
                context,
                0, notificationIntent, FLAG_UPDATE_CURRENT or FLAG_MUTABLE
            )
        } else {
            getActivity(
                context,
                0, notificationIntent, FLAG_UPDATE_CURRENT
            )
        }

        // 각 버튼들에 관한 Intent
        val prevIntent = Intent(context, MusicPlayerService::class.java)
        prevIntent.action = PREV
        val prevPendingIntent = PendingIntent
            .getService(context, 0, prevIntent, FLAG_MUTABLE)

        val playIntent = Intent(context, MusicPlayerService::class.java)
        playIntent.action = PLAY
        val playPendingIntent = PendingIntent
            .getService(context, 0, playIntent, FLAG_MUTABLE)

        val nextIntent = Intent(context, MusicPlayerService::class.java)
        nextIntent.action = NEXT
        val nextPendingIntent = PendingIntent
            .getService(context, 0, nextIntent, FLAG_MUTABLE)

        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentTitle("Music Player")
            .setContentText(title)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setOngoing(true) // true 일경우 알림 리스트에서 클릭하거나 좌우로 드래그해도 사라지지 않음
            .addAction(
                NotificationCompat.Action(
                    android.R.drawable.ic_media_previous,
                    "Prev", prevPendingIntent
                )
            )
            .addAction(
                NotificationCompat.Action(
                    android.R.drawable.ic_media_play,
                    "Play", playPendingIntent
                )
            )
            .addAction(
                NotificationCompat.Action(
                    android.R.drawable.ic_media_next,
                    "Next", nextPendingIntent
                )
            )
            .setContentIntent(pendingIntent)
            .build()

        val serviceChannel = NotificationChannel(
            CHANNEL_ID, CHANNEL_ID, NotificationManager.IMPORTANCE_DEFAULT
        )
        val manager = context.getSystemService(NotificationManager::class.java)
        manager?.createNotificationChannel(serviceChannel)

        return notification
    }
}