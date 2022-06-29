package org.sopt.anshim.workers

import android.content.Context
import android.util.Log
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.text.SimpleDateFormat
import java.util.*

class UploadWorker(context: Context, params: WorkerParameters): Worker(context, params) {
    override fun doWork(): Result {
        try {
            val count = inputData.getInt(KEY_COUNT_VALUE, 0)
            for (i in 0 until count) {
                Log.i(TAG, "Uploading $i")
            }

            val time = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
            val currentDate = time.format(Date())
            val outPutData = Data.Builder()
                .putString(KEY_WORKER, currentDate)
                .build()

            Log.i(TAG, "doWork: $outPutData")
            return Result.success(outPutData)
        } catch (e: Exception) {
            return Result.failure()
        }
    }

    companion object {
        const val TAG = "UploadWorker"
        const val KEY_COUNT_VALUE = "key_count_value"
        const val KEY_WORKER = "key_worker"
    }
}