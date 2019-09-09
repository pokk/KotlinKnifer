package com.devrapid.kotlinshaver

import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream

fun File.copyFrom(inputStream: InputStream): Boolean {
    return try {
        if (exists()) delete()
        FileOutputStream(this).use {
            try {
                val buffer = ByteArray(4096)
                var bytesRead: Int
                while (inputStream.read(buffer).also { bytesRead = it } >= 0) {
                    it.write(buffer, 0, bytesRead)
                }
            }
            finally {
                it.flush()
                try {
                    it.fd.sync()
                }
                catch (e: IOException) {
                }
            }
        }
        true
    }
    catch (e: IOException) {
        false
    }
}
