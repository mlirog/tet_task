package com.maris_skrivelis.tet_task.ui.search.models

import com.maris_skrivelis.tet_task.common.toDigits

data class Word (
    var wordAsString: String,
    var wordAsDigits: String = wordAsString.toDigits()
)
