[![](https://jitpack.io/v/sam38124/JzBarCodeScanner.svg)](https://jitpack.io/#sam38124/JzBarCodeScanner)
[![Platform](https://img.shields.io/badge/Platform-%20Android%20-brightgreen.svg)](https://github.com/sam38124)
[![characteristic](https://img.shields.io/badge/特點-%20輕量級%20%7C%20簡單易用%20%20%7C%20穩定%20-brightgreen.svg)](https://github.com/sam38124)
# JzBarCodeScanner
This is a util can very easily to scan barcode~
## List
* [How to import to project?](#Import)
* [Quick Start](#Use)
* [About me](#About)

<a name="Import"></a>
## How to introduce to the project?
> Support jcenter。 <br/>

### jcenter
Add into build.gradle 
```kotlin
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

Add into dependencies
```kotlin
dependencies {
implementation 'com.github.sam38124: JzBarCodeScanner:1.0'
}
```
<a name="Use"></a>
## Quick Start
### 1.Request Permission
```kotlin
Manifest.permission.CAMERA
```
### 2.Define a BarcodeView
```kotlin
//R.id.frame replace witf your view where position you want to display
//arrayOf(BarcodeFormat.CODE_128) define the barcode type what you want to sacn for
//Get the result from callback result
var scanView=BarCodeView(
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
                                    scanView.start()
                                }
                                .show()
                        }
                    })
```
### 3.Start Scanner
```kotlin
coder.start()
```
### 4.Stop Scanner
```kotlin
coder.stop()
```
### 5.Convert string to barcode image
```kotlin
var width=findViewById<ImageView>(R.id.imageView).width
var height=findViewById<ImageView>(R.id.imageView).height
findViewById<ImageView>(R.id.imageView).setImageBitmap("My bar code String".getBarcode(BarcodeFormat.CODE_128,width,height))
```
<a name="About"></a>
# About me
#### <font color="#0000dd"> Work for: </font><br /> 
+ ##### <font color="#660000">【Orange Electronic】</font><br /> 
#### <font color="#0000dd"> Position: </font><br /> 
+ ##### Full stack engineer<br/>  
#### <font color="#0000dd"> Main defense range: </font><br /> 
+ ##### Android and IOS(4 years)<br/>  
+ ##### Jsp(2 years)<br/> 
+ ##### Javascript and Jquery and Ktor(1 years)<br /> 
#### <font color="#0000dd"> Contact information: </font><br /> 
+  ##### line:sam38124<br /> 

+  ##### gmail:sam38124@gmail.com
