package com.maris_skrivelis.tet_task.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.maris_skrivelis.tet_task.App

inline fun <reified T : ViewModel> lazyViewModel(
    noinline owner: (() -> App),
    noinline creator: (() -> T)? = null
) = lazy {
    if (creator == null)
        ViewModelProvider(owner()).get(T::class.java)
    else
        ViewModelProvider(owner(), BaseViewModelFactory(creator)).get(T::class.java)
}

class BaseViewModelFactory<T>(val creator: () -> T) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return creator() as T
    }
}
