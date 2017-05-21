package com.devrapid.utils

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.devrapid.kotlinknifer.logd
import com.devrapid.kotlinknifer.loge

class MainActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        logw("hello world")
//        logv("hello world")
//        loge("hello world")
//        logi("hello world")
        logd("hello world")
        logd()
        loge("gere")
        loge()
        loge(ArithmeticException("gello"))
    }
}
