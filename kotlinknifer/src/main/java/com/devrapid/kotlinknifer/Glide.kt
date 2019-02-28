package com.devrapid.kotlinknifer

import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

fun <T> glideListener(listener: GlideRequestListenerWrapper<T>.() -> Unit): RequestListener<T> {
    val wrapper = GlideRequestListenerWrapper<T>().apply(listener)

    return object : RequestListener<T> {
        override fun onLoadFailed(
            e: GlideException?,
            model: Any?,
            target: Target<T>?,
            isFirstResource: Boolean
        ) =
            wrapper.onLoadFailed?.invoke(e, model, target, isFirstResource) ?: false

        override fun onResourceReady(
            resource: T,
            model: Any?,
            target: Target<T>?,
            dataSource: DataSource?,
            isFirstResource: Boolean
        ) =
            wrapper.onResourceReady?.invoke(resource, model, target, dataSource, isFirstResource) ?: false
    }
}

class GlideRequestListenerWrapper<T> {
    var onResourceReady: ((
        resource: T,
        model: Any?,
        target: Target<T>?,
        dataSource: DataSource?,
        isFirstResource: Boolean
    ) -> Boolean)? = null
    var onLoadFailed: ((e: GlideException?, model: Any?, target: Target<T>?, isFirstResource: Boolean) -> Boolean)? =
        null
}
