package com.adi_random.tracky.ui.main.readingList

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Created by Adrian Pascu on 24-Jul-20.
 */
class ReadingListViewModelFactory(private val app: Application, private val type: ReadingListType) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        ReadingListViewModel(app, type) as T
}