package com.devrapid.kotlinknifer

import android.annotation.SuppressLint
import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import com.devrapid.kotlinshaver.cast

@SuppressLint("MissingPermission")
fun Context.activeNetworkInfo() =
    (getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo

fun Context.isWifiConnected() = activeNetworkInfo().run { this != null && ConnectivityManager.TYPE_WIFI == type }

fun Context.isConnected() = activeNetworkInfo().run { this != null && isConnected }

@SuppressLint("MissingPermission")
fun hasNetwork(context: Context): Boolean {
    var isConnected = false // Initial Value
    val connectivityManager = cast<ConnectivityManager>(context.getSystemService(CONNECTIVITY_SERVICE))
    val activeNetwork = connectivityManager.activeNetworkInfo
    if (activeNetwork != null && activeNetwork.isConnected) {
        isConnected = true
    }
    return isConnected
}
