package com.maris_skrivelis.tet_task.injection

import com.maris_skrivelis.tet_task.ui.search.viewmodels.SearchViewModel

var viewModelProvider = ViewModelProvider()
    private set

open class ViewModelProvider {

    open fun provideLoginViewModel() = SearchViewModel()
}
