package com.jianzhi.barcodescanner

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.*
import androidx.fragment.app.Fragment
import com.google.zxing.BarcodeFormat
import com.jianzhi.jzbarcodescnner.BarCodeView
import com.jianzhi.jzbarcodescnner.callback
import com.jianzhi.jzbarcodescnner.getBarcode
import com.orange.jzchi.jzframework.JzActivity
import androidx.core.app.ActivityCompat.checkSelfPermission as checkSelfPermission1

class MainActivity : Activity() {
    lateinit var coder: BarCodeView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.button).setOnClickListener {RequestPermission()}
        RequestPermission()
    }

    fun RequestPermission() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    Manifest.permission.CAMERA
                )
            ) {
                AlertDialog.Builder(this)
                    .setCancelable(false)
                    .setTitle("需要相機權限")
                    .setMessage("需要相機權限才能掃描BARCODE!")
                    .setPositiveButton(
                        "確認"
                    ) { _, _ ->
                        requestPermissions(
                            this,
                            arrayOf(Manifest.permission.CAMERA),
                            1
                        )
                    }
                    .show()
            } else {
                requestPermissions(this, arrayOf(Manifest.permission.CAMERA), 1)
            }
        }else{
            findViewById<Button>(R.id.button).text = "停止掃描"
            coder =
                BarCodeView(
                    findViewById<FrameLayout>(R.id.frame),
                    arrayOf(BarcodeFormat.CODE_128),
                    object : callback {
                        override fun result(text: String) {
                            AlertDialog.Builder(this@MainActivity)
                                .setCancelable(false)
                                .setTitle("掃描到內容")
                                .setMessage(text)
                                .setPositiveButton(
                                    "確認"
                                ) { _, _ ->
                                    coder.start()
                                    var width=findViewById<ImageView>(R.id.imageView).width
                                    var height=findViewById<ImageView>(R.id.imageView).height
//                                    findViewById<ImageView>(R.id.imageView).setImageBitmap(text.getBarcode(BarcodeFormat.CODE_128,width,height))
                                }
                                .show()
                        }
                    })
            findViewById<Button>(R.id.button).setOnClickListener {
                if (coder.isstart) {
                    findViewById<Button>(R.id.button).text = "繼續掃描"
                    coder.stop()
                } else {
                    findViewById<Button>(R.id.button).text = "停止掃描"
                    coder.start()
                }

            }
        }
    }
}