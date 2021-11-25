package com.maris_skrivelis.tet_task.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import com.maris_skrivelis.tet_task.common.PROVIDED_FILE_NAME
import com.maris_skrivelis.tet_task.common.getFileFromAssets
import com.maris_skrivelis.tet_task.common.launchMain
import com.maris_skrivelis.tet_task.common.showSnackbar
import com.maris_skrivelis.tet_task.databinding.FragmentSearchBinding
import com.maris_skrivelis.tet_task.ui.search.adapters.SearchResultsAdapter
import kotlinx.coroutines.flow.collect
import timber.log.Timber

class SearchFragment: SearchBaseFragment() {

    private lateinit var binding: FragmentSearchBinding
    private val adapter by lazy {
        SearchResultsAdapter { selectedItem ->
           binding.root.showSnackbar(selectedItem)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSearchBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.resultList.adapter = adapter

        launchMain {
            viewModel.isLoading.collect { status ->
                binding.loadingIndicator.visibility = when (status) {
                    true -> View.VISIBLE
                    false -> View.GONE
                }
            }
        }

        launchMain {
            viewModel.wordList.collect { wordList ->
                adapter.words = wordList
            }
        }

        viewModel.readFile(getFileFromAssets(requireContext(), PROVIDED_FILE_NAME).absolutePath)
        viewModel.loadFileWords()

        binding.numberInput.doAfterTextChanged {
            if (binding.numberInput.text.toString().isNotBlank()) {
                viewModel.filterResults(binding.numberInput.text.toString())
            } else {
                viewModel.loadFileWords()
            }
        }
    }
}
