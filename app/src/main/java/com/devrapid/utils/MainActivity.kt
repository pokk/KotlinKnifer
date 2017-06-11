package com.devrapid.utils

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.devrapid.kotlinknifer.logd
import com.devrapid.kotlinknifer.loge

class MainActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tv = findViewById(R.id.tv_text)
        
        
        logd("hello world")
        logd()
        loge("gere")
        loge()
        loge(ArithmeticException("gello"))
    }
}
