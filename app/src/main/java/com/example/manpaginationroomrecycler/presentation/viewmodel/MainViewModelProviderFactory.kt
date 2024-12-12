package com.example.manpaginationroomrecycler.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.manpaginationroomrecycler.domain.repository.MainDataRepository

class MainViewModelProviderFactory(
    private val repository: MainDataRepository,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("unchecked cast")
            return MainViewModel(repository) as T
        } else {
            throw IllegalArgumentException("wrong view model")
        }
    }
}