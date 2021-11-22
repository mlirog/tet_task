package com.maris_skrivelis.tet_task.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.maris_skrivelis.tet_task.databinding.FragmentSearchBinding

class SearchFragment: SearchBaseFragment() {

    private lateinit var binding: FragmentSearchBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSearchBinding.inflate(inflater)
        return binding.root
    }
}
