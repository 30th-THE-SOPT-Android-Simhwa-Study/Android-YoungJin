package org.sopt.anshim.presentation.component

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.annotation.RequiresApi
import org.sopt.anshim.R
import org.sopt.anshim.util.Actions.NEXT
import org.sopt.anshim.util.Actions.PLAY
import org.sopt.anshim.util.Actions.PREV
import org.sopt.anshim.util.Actions.START_FOREGROUND
import org.sopt.anshim.util.Actions.STOP_FOREGROUND

class MusicPlayerService : Service() {
    private var player: MediaPlayer? = null
    private var music = intArrayOf(R.raw.sample_1, R.raw.sample_2, R.raw.sample_3)
    private var pos = 0

    override fun onCreate() {
        super.onCreate()
        player = MediaPlayer.create(this, music[pos])
        player?.isLooping = true
    }

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            START_FOREGROUND -> {
                Log.d(TAG, "START_FOREGROUND")
                startForegroundService()
            }

            STOP_FOREGROUND -> {
                Log.d(TAG, "STOP_FOREGROUND")
                stopForegroundService()
            }

            PREV -> {
                val tmpPos = (pos - 1) % music.size
                playMusic(if (tmpPos < 0) music.size + tmpPos else tmpPos)
            }

            PLAY -> {
                if (player?.isPlaying == true) {
                    stopMusic()
                } else {
                    playMusic(pos)
                }
            }

            NEXT -> {
                playMusic((pos + 1) % music.size)
            }

            null -> {
                // intent가 시스템에 의해 재생성되었을때 null값이므로 이 부분도 고려하여 처리
            }
        }

        return START_STICKY
    }

    @RequiresApi(Build.VERSION_CODES.S)
    private fun startForegroundService() {
        val notification = MusicNotification.createNotification(this)
        startForeground(NOTIFICATION_ID, notification)
    }

    private fun stopForegroundService() {
        stopMusic()
        stopForeground(true)
        stopSelf()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        stopMusic()
    }

    private fun playMusic(position: Int) {
        stopMusic()
        pos = position
        player = MediaPlayer.create(this, music[pos])
        player?.start()
    }

    private fun stopMusic() {
        player?.stop()
        player?.release()
        player = null
    }

    companion object {
        private const val TAG = "MusicPlayerService"
        private const val NOTIFICATION_ID = 20
    }
}