package com.jianzhi.jzbarcodescnner

import android.graphics.Bitmap
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import com.jianzhi.jzbarcodescnner.encoder.encodeAsBitmap
import java.util.*

object encoder {
    @Throws(WriterException::class)
    fun encodeAsBitmap(
        contents: String,
        format: BarcodeFormat,
        desiredWidth: Int,
        desiredHeight: Int
    ): Bitmap? {
        if (contents.length == 0) return null
        val WHITE = -0x1
        val BLACK = -0x1000000
        var hints: HashMap<EncodeHintType?, String?>? = null
        var encoding: String? = null
        for (i in 0 until contents.length) {
            if (contents[i].toInt() > 0xFF) {
                encoding = "UTF-8"
                break
            }
        }
        if (encoding != null) {
            hints = HashMap(2)
            hints[EncodeHintType.CHARACTER_SET] = encoding
        }
        val writer = MultiFormatWriter()
        val result = writer.encode(contents, format, desiredWidth, desiredHeight, hints)
        val width = result.width
        val height = result.height
        val pixels = IntArray(width * height)
        for (y in 0 until height) {
            val offset = y * width
            for (x in 0 until width) {
                pixels[offset + x] = if (result[x, y]) BLACK else WHITE
            }
        }
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height)
        return bitmap
    }
}
fun String.getBarcode(format:BarcodeFormat,desiredWidth: Int, desiredHeight: Int):Bitmap?{
    return encodeAsBitmap(this,format,desiredWidth,desiredHeight)
}