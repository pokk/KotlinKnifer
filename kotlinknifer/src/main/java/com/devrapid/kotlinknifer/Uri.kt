@file:Suppress("NOTHING_TO_INLINE")

package com.devrapid.kotlinknifer

import android.content.ContentUris
import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.DocumentsContract
import android.provider.MediaStore

/**
 * @author  Jieyi Wu
 * @since   2018/03/23
 */
/**
 * For above [Build.VERSION_CODES.KITKAT], convert the uri to the absolute path.
 */
fun Uri.toPath(context: Context): String? {
    val isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT

    // DocumentProvider
    return if (isKitKat && DocumentsContract.isDocumentUri(context, this)) {
        val docId = DocumentsContract.getDocumentId(this)
        val split = docId
            .split(":".toRegex())
            .dropLastWhile(String::isEmpty)
            .toTypedArray()
        val type = split[0]

        // ExternalStorageProvider
        return if (isExternalStorageDocument()) {
            if ("primary".equals(type, ignoreCase = true)) {
                "${Environment.getExternalStorageDirectory()}/${split[1]}"
            }
            // TODO handle non-primary volumes
            else null
        }
        // DownloadsProvider
        else if (isDownloadsDocument()) {
            val contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"),
                                                        java.lang.Long.valueOf(docId))

            getDataColumn(context, contentUri)
        }
        // MediaProvider
        else if (isMediaDocument()) {
            val contentUri = when (type) {
                "image" -> MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                "video" -> MediaStore.Video.Media.EXTERNAL_CONTENT_URI
                "audio" -> MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
                else -> null
            }

            val selection = "_id=?"
            val selectionArgs = arrayOf(split[1])

            getDataColumn(context, contentUri, selection, selectionArgs)
        }
        else null
    }
    // MediaStore (and general)
    else if ("content".equals(scheme, ignoreCase = true)) {
        getDataColumn(context, this)
    }
    // File
    else if ("file".equals(scheme, ignoreCase = true)) {
        path
    }
    else null
}

/**
 * Get the value of the data column for this Uri. This is useful for
 * MediaStore Uris, and other file-based ContentProviders.
 *
 * @param context The context.
 * @param uri The Uri to query.
 * @param selection (Optional) Filter used in the query.
 * @param selectionArgs (Optional) Selection arguments used in the query.
 * @return The value of the _data column, which is typically a file path.
 */
internal fun getDataColumn(
    context: Context,
    uri: Uri?,
    selection: String? = null,
    selectionArgs: Array<String>? = null
): String? {
    val column = "_data"
    val projection = arrayOf(column)
    val cursor = context.contentResolver.query(uri, projection, selection, selectionArgs, null)

    cursor.use {
        if (it.moveToFirst()) {
            val columnIndex = it.getColumnIndexOrThrow(column)

            return it.getString(columnIndex)
        }
    }
    return null
}

/**
 * The Uri authority is ExternalStorageProvider.
 */
inline fun Uri.isExternalStorageDocument() = "com.android.externalstorage.documents" == authority

/**
 * The Uri authority is DownloadsProvider.
 */
inline fun Uri.isDownloadsDocument() = "com.android.providers.downloads.documents" == authority

/**
 * The Uri authority is MediaProvider.
 */
inline fun Uri.isMediaDocument() = "com.android.providers.media.documents" == authority