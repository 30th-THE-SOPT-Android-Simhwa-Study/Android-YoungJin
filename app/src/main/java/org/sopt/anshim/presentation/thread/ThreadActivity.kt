package org.sopt.anshim.presentation.thread

import android.graphics.Bitmap
import android.os.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.anshim.R
import org.sopt.anshim.databinding.ActivityThreadBinding
import org.sopt.anshim.presentation.types.ProgressState
import org.sopt.anshim.util.ConvertBitmap
import org.sopt.anshim.util.getBitmapFromURL

@AndroidEntryPoint
class ThreadActivity : AppCompatActivity() {
    private lateinit var binding: ActivityThreadBinding
    private val viewModel: ThreadViewModel by viewModels()
    lateinit var handlerThread: HandlerThread
    private lateinit var myHandler: MyHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            DataBindingUtil.setContentView<ActivityThreadBinding?>(this, R.layout.activity_thread)
                .also {
                    it.viewModel = viewModel
                    it.lifecycleOwner = this@ThreadActivity
                }

        myHandler = MyHandler(viewModel::setImage, viewModel::setCount, viewModel::setLoadingState)
        handlerThread = HandlerThread("HandlerThread-1")
        handlerThread.start()

        initLayout()
    }

    private fun initLayout() {
        binding.image1.setOnClickListener {
            Image1Thread(myHandler).start()
        }
        binding.image2.setOnClickListener {
            Image2Thread(myHandler).start()
        }
        binding.profileImage.setOnClickListener {
            CountThread(myHandler).start()
            it.isEnabled = false // CountThread가 한번만 start 되도록 하기 위함
        }
    }

    class MyHandler(
        private val setImage: (Bitmap) -> (Unit),
        private val setCount: (Int) -> (Unit),
        private val setLoadingState: (ProgressState) -> (Unit),
    ) :
        Handler(Looper.getMainLooper()) {
        private var progressState = ProgressState.IDLE

        override fun handleMessage(msg: Message) {
            // 다른 Thread에서 전달받은 Message 처리
            when (msg.what) {
                1, 2 -> {
                    // UI 작업
                    (msg.obj as? String)?.let {
                        setImage(ConvertBitmap().stringToBitmap(it) ?: return@let)
                        changeProgressState(ProgressState.IDLE)
                    }
                }
                3 -> {
                    setCount(msg.obj as? Int ?: return)
                }
            }
        }

        fun changeProgressState(state: ProgressState) {
            progressState = state
            setLoadingState(state)
        }

        fun getProgressState(): ProgressState = progressState
    }

    class Image1Thread(private val myHandler: MyHandler) : Thread() {
        override fun run() {
            // Implement Image 1
            if (myHandler.getProgressState() == ProgressState.IN_PROGRESS) return
            myHandler.changeProgressState(ProgressState.IN_PROGRESS)
            val bitmap = getBitmapFromURL("https://avatars.githubusercontent.com/u/48701368?v=4")
            val msg = myHandler.obtainMessage().apply {
                what = 1
                bitmap?.let { obj = ConvertBitmap().bitmapToString(it) }
            }
            sleep(2000L)
            myHandler.sendMessage(msg)
        }
    }

    class Image2Thread(private val myHandler: MyHandler) : Thread() {
        override fun run() {
            // Implement Image 2
            if (myHandler.getProgressState() == ProgressState.IN_PROGRESS) return
            myHandler.changeProgressState(ProgressState.IN_PROGRESS)
            val bitmap = getBitmapFromURL("https://avatars.githubusercontent.com/u/62291759?v=4")
            val msg = myHandler.obtainMessage().apply {
                what = 2
                bitmap?.let { obj = ConvertBitmap().bitmapToString(it) }
            }
            sleep(2000L)
            myHandler.sendMessage(msg)
        }
    }

    class CountThread(private val myHandler: MyHandler) : Thread() {
        override fun run() {
            // Implement Count
            if (!isAlive) return
            var count = 0
            while (true) {
                val msg = myHandler.obtainMessage().apply {
                    obj = count
                    what = 3
                }
                count++
                sleep(1000L)
                myHandler.sendMessage(msg)
            }
        }
    }
}