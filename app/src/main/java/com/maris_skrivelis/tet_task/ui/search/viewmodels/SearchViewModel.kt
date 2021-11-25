package com.maris_skrivelis.tet_task.ui.search.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import timber.log.Timber
import java.io.File
import java.io.InputStream

class SearchViewModel: ViewModel()  {

    private val _lineList = MutableSharedFlow<List<String>>(replay = 1)

    val lineList = _lineList

    fun readFile(filePath: String) {
        Timber.d("PATH $filePath")
        _lineList.tryEmit(emptyList())

        val inputStream: InputStream = File(filePath).inputStream()
        val mutableLineList = mutableListOf<String>()

        inputStream.bufferedReader().useLines { lines ->
            lines.forEach { line ->
                mutableLineList.add(line)
            }
        }

        _lineList.tryEmit(mutableLineList)
    }
}
