package com.jianzhi.jzbarcodescnner

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import com.google.zxing.BarcodeFormat
import com.google.zxing.Result
import com.jianzhi.jzbarcodescnner.R
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
    var ALL_FORMATS: ArrayList<BarcodeFormat> = ArrayList(1)
    var isstart=true
    override fun handleResult(rawResult: Result) {
        callback.result(rawResult.text)
        Log.e("handleResult",rawResult.text)
        mScannerView!!.setResultHandler(this)
        isstart=false
    }

    init {
        mScannerView = ZXingScannerView(view.context)
        ALL_FORMATS.addAll(barcodeFormat)
        mScannerView!!.setFormats(ALL_FORMATS)
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