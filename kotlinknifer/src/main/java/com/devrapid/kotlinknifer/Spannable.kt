@file:Suppress("NOTHING_TO_INLINE")

package com.devrapid.kotlinknifer

import android.text.Html
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.core.text.parseAsHtml

inline fun TextView.withMovement() = apply {
    movementMethod = LinkMovementMethod.getInstance()
}

inline fun TextView.parseAsHtml(
    flags: Int = HtmlCompat.FROM_HTML_MODE_LEGACY,
    imageGetter: Html.ImageGetter? = null,
    tagHandler: Html.TagHandler? = null,
) = apply {
    text = text.toString().parseAsHtml(flags, imageGetter, tagHandler)
}

fun clickableSpan(block: (widget: View) -> Unit) = object : ClickableSpan() {
    override fun onClick(widget: View) {
        block(widget)
    }
}
