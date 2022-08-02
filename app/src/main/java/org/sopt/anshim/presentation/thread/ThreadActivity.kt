package org.sopt.anshim.presentation.thread

import android.os.*
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import coil.load
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.anshim.R
import org.sopt.anshim.databinding.ActivityThreadBinding
import org.sopt.anshim.util.ConvertBitmap
import org.sopt.anshim.util.getBitmapFromURL

@AndroidEntryPoint
class ThreadActivity : AppCompatActivity() {
    private lateinit var binding: ActivityThreadBinding
    lateinit var handlerThread: HandlerThread
    private lateinit var myHandler: MyHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_thread)

        myHandler = MyHandler(binding.profileImage, binding.name)
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

    class MyHandler(private val imageView: ImageView, private val textView: TextView) :
        Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            // 다른 Thread에서 전달받은 Message 처리
            when (msg.what) {
                1, 2 -> {
                    // UI 작업
                    msg.data.getString(ARG_IMAGE)?.let {
                        val bitmap = ConvertBitmap().stringToBitmap(it)
                        imageView.load(bitmap)
                    }
                }
                3 -> {
                    textView.text = msg.data.getInt(ARG_COUNT).toString()
                }
            }
        }
    }

    class Image1Thread(private val myHandler: MyHandler) : Thread() {
        override fun run() {
            // Implement Image 1
            val bitmap = getBitmapFromURL("https://avatars.githubusercontent.com/u/48701368?v=4")
            val msg = myHandler.obtainMessage().apply {
                data = bundleOf(ARG_IMAGE to ConvertBitmap().bitmapToString(bitmap ?: return))
                what = 1
            }
            myHandler.sendMessage(msg)
        }
    }

    class Image2Thread(private val myHandler: MyHandler) : Thread() {
        override fun run() {
            // Implement Image 2
            val bitmap = getBitmapFromURL("https://avatars.githubusercontent.com/u/62291759?v=4")
            val msg = myHandler.obtainMessage().apply {
                data = bundleOf(ARG_IMAGE to ConvertBitmap().bitmapToString(bitmap ?: return))
                what = 2
            }
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
                    data = bundleOf(ARG_COUNT to count)
                    what = 3
                }
                count++
                sleep(1000L)
                myHandler.sendMessage(msg)
            }
        }
    }

    companion object {
        private const val ARG_IMAGE = "image"
        private const val ARG_COUNT = "count"
    }
}