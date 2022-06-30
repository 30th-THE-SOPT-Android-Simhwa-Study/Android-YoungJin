package org.sopt.anshim.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.work.*
import org.sopt.anshim.R
import org.sopt.anshim.databinding.ActivityWorkManagerBinding
import org.sopt.anshim.workers.*
import org.sopt.anshim.workers.UploadWorker.Companion.KEY_COUNT_VALUE

class WorkManagerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWorkManagerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_work_manager)

        val workManger = WorkManager.getInstance(applicationContext)
        val data = Data.Builder()
            .putInt(KEY_COUNT_VALUE, 125)
            .build()
        val constraints = Constraints.Builder()
            .setRequiresCharging(true)
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val compressingRequest = OneTimeWorkRequest.Builder(CompressingWorker::class.java)
            .build()
        val deleteRequest = OneTimeWorkRequest.Builder(DeleteWorker::class.java)
            .build()
        val downloadingRequest = OneTimeWorkRequest.Builder(DownloadingWorker::class.java)
            .build()
        val filteringRequest = OneTimeWorkRequest.Builder(FilteringWorker::class.java)
            .build()
        val uploadingRequest = OneTimeWorkRequest.Builder(UploadWorker::class.java)
            .setConstraints(constraints)
            .setInputData(data)
            .build()

        val chain1 = workManger.beginWith(compressingRequest).then(deleteRequest)
        val chain2 = workManger.beginWith(downloadingRequest).then(filteringRequest)
        val chain3 = WorkContinuation.combine(listOf(chain1, chain2)).then(uploadingRequest)
        chain3.enqueue()

        workManger.getWorkInfoByIdLiveData(uploadingRequest.id).observe(this) {
                val outputData = it.outputData
                binding.text.text = it.state.name

                if (it.state.isFinished) {
                    val message = outputData.getString(UploadWorker.KEY_WORKER) ?: "123"
                    Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
                }
        }
    }
}