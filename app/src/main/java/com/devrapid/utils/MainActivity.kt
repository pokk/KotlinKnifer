package com.devrapid.utils

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.devrapid.kotlinknifer.toast

class MainActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toast("Hello World")
    }
}
