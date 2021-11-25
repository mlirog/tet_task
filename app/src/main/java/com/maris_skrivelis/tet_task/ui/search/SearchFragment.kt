package com.maris_skrivelis.tet_task.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import com.google.android.material.snackbar.Snackbar
import com.maris_skrivelis.tet_task.R
import com.maris_skrivelis.tet_task.common.PROVIDED_FILE_NAME
import com.maris_skrivelis.tet_task.common.launchMain
import com.maris_skrivelis.tet_task.common.showSnackbar
import com.maris_skrivelis.tet_task.databinding.FragmentSearchBinding
import com.maris_skrivelis.tet_task.ui.search.adapters.SearchResultsAdapter
import kotlinx.coroutines.flow.collect
import java.io.FileNotFoundException
import java.lang.Exception

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

        prepareResults()
    }

    private fun prepareResults() {
        try {
            viewModel.readFile(requireContext(), PROVIDED_FILE_NAME)
            viewModel.loadFileWords()

            binding.numberInput.doAfterTextChanged {
                if (binding.numberInput.text.toString().isNotBlank()) {
                    viewModel.filterResults(binding.numberInput.text.toString())
                } else {
                    viewModel.loadFileWords()
                }
            }
        } catch (e: FileNotFoundException) {
            binding.root.showSnackbar(getString(R.string.error_loading_file), Snackbar.LENGTH_INDEFINITE
            ) { prepareResults() }
        } catch (e: Exception) {
            binding.root.showSnackbar(e.localizedMessage, Snackbar.LENGTH_INDEFINITE
            ) { prepareResults() }
        }
    }
}
