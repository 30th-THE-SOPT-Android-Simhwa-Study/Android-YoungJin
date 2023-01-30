package org.sopt.anshim.application

import android.app.Application
import androidx.databinding.ktx.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import org.sopt.anshim.util.AnshimDebugTree
import timber.log.Timber

@HiltAndroidApp
class AnshimApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(AnshimDebugTree())
        }
    }
}