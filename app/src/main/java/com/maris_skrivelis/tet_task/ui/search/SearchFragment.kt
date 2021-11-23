package com.maris_skrivelis.tet_task.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.maris_skrivelis.tet_task.common.PROVIDED_FILE_NAME
import com.maris_skrivelis.tet_task.common.getFileFromAssets
import com.maris_skrivelis.tet_task.databinding.FragmentSearchBinding
import com.maris_skrivelis.tet_task.ui.search.adapters.SearchResultsAdapter
import timber.log.Timber

class SearchFragment: SearchBaseFragment() {

    private lateinit var binding: FragmentSearchBinding
    private val adapter by lazy {
        SearchResultsAdapter {
           // Do something
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSearchBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.readFile(getFileFromAssets(requireContext(), PROVIDED_FILE_NAME).absolutePath)
        binding.resultList.adapter = adapter

        launchMain {
            viewModel.avatarList.collect { colors ->
                adapter.avatars = colors
            }
        }
    }
}
