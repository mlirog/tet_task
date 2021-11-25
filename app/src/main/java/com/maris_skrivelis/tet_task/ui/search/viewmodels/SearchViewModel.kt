package com.maris_skrivelis.tet_task.ui.search.viewmodels

import androidx.lifecycle.ViewModel
import com.maris_skrivelis.tet_task.common.launchIO
import com.maris_skrivelis.tet_task.ui.search.models.Word
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import java.io.File

class SearchViewModel: ViewModel()  {

    private val _isLoading = MutableSharedFlow<Boolean>(replay = 1)
    private val _fileWords = mutableListOf<Word>()
    private val _wordList = MutableSharedFlow<List<Word>>(replay = 1)

    val isLoading: SharedFlow<Boolean> = _isLoading
    val wordList: SharedFlow<List<Word>> = _wordList

    fun readFile(filePath: String) {
        File(filePath).forEachLine { line ->
            if (line.isNotBlank()) {
                _fileWords.add(
                    Word(line)
                )
            }
        }
    }

    fun filterResults(wordAsDigits: String)  = launchIO {
        val foundWords = mutableListOf<Word>()

        _isLoading.emit(true)
        _fileWords.forEach { word ->
            if (word.wordAsDigits.contains(wordAsDigits)) {
                foundWords.add(word)
            }
        }
        _wordList.emit(foundWords.toTypedArray().toList())
        _isLoading.emit(false)
    }

    fun loadFileWords() {
        _wordList.tryEmit(_fileWords.toTypedArray().toList())
    }
}
