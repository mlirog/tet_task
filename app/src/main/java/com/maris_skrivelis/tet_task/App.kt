package com.maris_skrivelis.tet_task

import android.app.Application
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import com.maris_skrivelis.tet_task.common.LineNumberDebugTree
import timber.log.Timber

class App : Application(), ViewModelStoreOwner {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(LineNumberDebugTree())
        }
    }

    override fun getViewModelStore() = appViewModelStore

    companion object {

        private val appViewModelStore: ViewModelStore by lazy { ViewModelStore() }
    }
}
