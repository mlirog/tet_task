package com.maris_skrivelis.tet_task.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.maris_skrivelis.tet_task.R
import com.maris_skrivelis.tet_task.common.PROVIDED_FILE_NAME
import com.maris_skrivelis.tet_task.common.getFileFromAssets
import com.maris_skrivelis.tet_task.databinding.FragmentSearchBinding
import java.io.FileNotFoundException

class SearchFragment: SearchBaseFragment() {

    private lateinit var binding: FragmentSearchBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSearchBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        try {
            viewModel.readFile(getFileFromAssets(requireContext(), PROVIDED_FILE_NAME).absolutePath)
        } catch (e: FileNotFoundException) {
            binding.root.showSnackbar(R.string.error_reading_file)
        }
    }
}
