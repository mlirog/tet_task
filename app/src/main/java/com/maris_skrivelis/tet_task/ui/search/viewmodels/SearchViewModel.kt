package com.maris_skrivelis.tet_task.ui.search.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import com.maris_skrivelis.tet_task.common.launchIO
import com.maris_skrivelis.tet_task.ui.search.models.Word
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import java.io.FileNotFoundException
import java.lang.Exception

class SearchViewModel: ViewModel()  {

    private val _isLoading = MutableSharedFlow<Boolean>(replay = 1)
    private val _fileWords = mutableListOf<Word>()
    private val _wordList = MutableSharedFlow<List<Word>>(replay = 1)

    val isLoading: SharedFlow<Boolean> = _isLoading
    val wordList: SharedFlow<List<Word>> = _wordList

    fun readFile(context: Context, fileName: String) {
        try {
            context.assets.open(fileName).reader().forEachLine { line ->
                _fileWords.add(Word(line))
            }
        } catch (e: Exception) {
            //  Fragment class handles this and shows snackbar for it
            throw FileNotFoundException()
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
