package com.devrapid.utils

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.devrapid.kotlinknifer.obtainViewStub
import com.devrapid.kotlinknifer.showViewStub

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showViewStub(R.id.vs_a, R.id.v_a) {
            this.findViewById<Button>(R.id.btn_a).text = "HHH"
        }
        obtainViewStub(R.id.vs_a, R.id.v_a).findViewById<Button>(R.id.btn_a).text = "BBBBBBBBBBBBBB"
    }
}
