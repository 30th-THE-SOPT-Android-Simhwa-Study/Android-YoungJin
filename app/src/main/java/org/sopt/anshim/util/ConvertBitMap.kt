package org.sopt.anshim.util

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import java.io.ByteArrayOutputStream

class ConvertBitmap {
    fun bitmapToString(bitmap: Bitmap): String {
        // 바이트 배열을 차례대로 읽어 들이기위한 ByteArrayOutputStream클래스 선언
        val baos = ByteArrayOutputStream()

        // bitmap을 압축 (70%로 압축)
        bitmap.compress(Bitmap.CompressFormat.PNG, 70, baos)

        // 해당 bitmap을 byte배열로 바꿔줌
        val bytes = baos.toByteArray()

        // Base 64방식으로 byte 배열을 String으로 변환
        return Base64.encodeToString(bytes, Base64.DEFAULT)
    }

    fun stringToBitmap(encodedString: String?): Bitmap? {
        return try {
            // String화 된 이미지를  base64방식으로 인코딩하여 byte배열을 만듦
            val encodeByte: ByteArray = Base64.decode(encodedString, Base64.DEFAULT)

            // byte배열을 bitmapfactory 메소드를 이용하여 비트맵으로 바꿔줌
            BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.size)
        } catch (e: Exception) {
            e.message
            null
        }
    }
}