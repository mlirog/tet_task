package com.maris_skrivelis.tet_task.common

import android.content.Context
import java.io.File

fun getFileFromAssets(context: Context, fileName: String): File {
    return File(context.cacheDir, fileName).also {
        if (!it.exists()) {
            it.outputStream().use { cache ->
                context.assets.open(fileName).use { inputStream ->
                    inputStream.copyTo(cache)
                }
            }
        }
    }
}
