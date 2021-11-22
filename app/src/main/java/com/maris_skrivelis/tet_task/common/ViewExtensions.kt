package com.maris_skrivelis.tet_task.common

import android.os.Build
import android.view.WindowInsets
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

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
