package com.maris_skrivelis.tet_task.ui.search

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.maris_skrivelis.tet_task.App
import com.maris_skrivelis.tet_task.common.lazyViewModel
import com.maris_skrivelis.tet_task.common.showFullscreen
import com.maris_skrivelis.tet_task.injection.viewModelProvider

open class SearchBaseFragment: Fragment() {

    open var showFullscreen: Boolean = true

    val viewModel by lazyViewModel(
        { requireActivity().application as App },
        { viewModelProvider.provideLoginViewModel() }
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showFullscreen(showFullscreen)
    }
}
