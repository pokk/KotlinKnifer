@file:Suppress("NOTHING_TO_INLINE")

package com.devrapid.kotlinknifer

import android.content.ContentUris
import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.provider.OpenableColumns
import java.io.File

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
            else {
                "storage/${docId.replace(":", "/")}"
            }
        }
        // DownloadsProvider
        else if (isDownloadsDocument()) {
            getFilePath(context, this)?.let {
                return "${Environment.getExternalStorageDirectory()}/Download/$it"
            }
            val contentUri =
                ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), docId.toLong())
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

            if (contentUri == null) return null
            getDataColumn(context, contentUri, selection, selectionArgs)
        }
        else null
    }
    // MediaStore (and general)
    else if ("content".equals(scheme, ignoreCase = true)) {
        // Return the remote address.
        if (isGooglePhotosUri()) {
            return lastPathSegment
        }
        getDataColumn(context, this)
    }
    // File
    else if ("file".equals(scheme, ignoreCase = true)) {
        path
    }
    else null
}

fun Uri.getRealFileName(context: Context): String {
    val uriString = toString()
    val file = File(uriString)
    return when {
        uriString.startsWith("content://") ->
            context.contentResolver.query(this, null, null, null, null)?.use {
                if (it.moveToFirst()) {
                    it.getString(it.getColumnIndex(OpenableColumns.DISPLAY_NAME))
                }
                else
                    null
            }.orEmpty()
        uriString.startsWith("file://") -> file.name.orEmpty()
        else -> ""
    }
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
    uri: Uri,
    selection: String? = null,
    selectionArgs: Array<String>? = null
): String? {
    val column = "_data"
    val projection = arrayOf(column)

    return context.contentResolver.query(uri, projection, selection, selectionArgs, null)?.use {
        if (it.moveToFirst()) {
            val columnIndex = it.getColumnIndexOrThrow(column)

            it.getString(columnIndex)
        }
        else
            null
    }
}

internal fun getFilePath(context: Context, uri: Uri): String? {
    val projection = arrayOf(MediaStore.MediaColumns.DISPLAY_NAME)

    return context.contentResolver.query(uri, projection, null, null, null)?.use {
        if (it.moveToFirst()) {
            val index = it.getColumnIndexOrThrow(MediaStore.MediaColumns.DISPLAY_NAME)
            return it.getString(index)
        }
        else
            null
    }
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

/**
 * The Uri authority is Google Photos Uri.
 */
inline fun Uri.isGooglePhotosUri() = "com.google.android.apps.photos.content" == authority
