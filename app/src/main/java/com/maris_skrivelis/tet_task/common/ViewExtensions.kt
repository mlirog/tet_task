package com.maris_skrivelis.tet_task.common

import android.os.Build
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.maris_skrivelis.tet_task.R

@Suppress("DEPRECATION")
fun Fragment.showFullscreen(show: Boolean = true) {
    when (show) {
        true -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                (requireActivity() as AppCompatActivity).window.insetsController?.hide(WindowInsets.Type.statusBars())
            } else {
                (requireActivity() as AppCompatActivity).window.setFlags(
                    WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN
                )
            }
        }
        false -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                (requireActivity() as AppCompatActivity).window.insetsController?.show(WindowInsets.Type.statusBars())
            } else {
                (requireActivity() as AppCompatActivity).window.clearFlags(
                    WindowManager.LayoutParams.FLAG_FULLSCREEN
                )
            }
        }
    }
}

fun View.showSnackbar(message: String, length: Int = Snackbar.LENGTH_LONG, onRetry: (() -> Unit)? = null) {
    val snackbar = Snackbar.make(this, message, length)
    if (onRetry != null) {
        snackbar.setAction(context.getString(R.string.retry)) {
            snackbar.dismiss()
            onRetry()
        }
    }
    snackbar.show()
}
