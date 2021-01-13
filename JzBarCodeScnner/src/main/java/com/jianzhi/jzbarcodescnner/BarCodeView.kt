package com.jianzhi.jzbarcodescnner

import android.graphics.Bitmap
import android.util.Log
import android.view.View
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.MultiFormatWriter
import com.google.zxing.Result
import kotlinx.android.synthetic.main.fragment_frag__function__qrcode_scanner.view.*
import me.dm7.barcodescanner.zxing.ZXingScannerView

interface callback {
    fun result(text: String)
}

class BarCodeView(
    view: View,
    barcodeFormat: Array<BarcodeFormat>,
    var callback: callback
) : ZXingScannerView.ResultHandler {
    private var mScannerView: ZXingScannerView? = null
    var isstart=true
    override fun handleResult(rawResult: Result) {
        callback.result(rawResult.text)
        Log.e("handleResult",rawResult.text)
        mScannerView!!.setResultHandler(this)
        isstart=false
    }

    init {
        mScannerView = ZXingScannerView(view.context)
        mScannerView!!.setFormats(barcodeFormat.toList())
        mScannerView!!.resumeCameraPreview(this)
        mScannerView!!.setAutoFocus(true)
        mScannerView!!.setAspectTolerance(0.0f)
        view.frame.addView(mScannerView)
        mScannerView!!.setResultHandler(this)
        mScannerView!!.startCamera()
    }

    fun start() {
        isstart=true
        mScannerView!!.startCamera()
    }

    fun stop() {
        isstart=false
        mScannerView!!.stopCamera()
    }
}
