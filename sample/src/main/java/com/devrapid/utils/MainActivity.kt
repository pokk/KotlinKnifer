package com.devrapid.utils

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.devrapid.kotlinknifer.iff

class MainActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun test() {
        {} iff true
    }
}