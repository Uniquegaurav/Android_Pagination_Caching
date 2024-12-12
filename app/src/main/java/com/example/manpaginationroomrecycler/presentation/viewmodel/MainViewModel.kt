package com.example.manpaginationroomrecycler.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.manpaginationroomrecycler.domain.model.Item
import com.example.manpaginationroomrecycler.domain.repository.MainDataRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainViewModel(private val mainDataRepository: MainDataRepository) : ViewModel() {
    private val _data = MutableStateFlow<List<Item>>(emptyList())
    val data = _data.asStateFlow()
    private var currentPage = 1

    init {
        loadCacheData()
        fetchData()
    }

    private fun loadCacheData() = viewModelScope.launch {
        mainDataRepository.getAllItems().collectLatest {
            _data.value = it
        }
    }

    fun fetchData() = viewModelScope.launch {
        val result = mainDataRepository.fetchItemsFromServer(page = currentPage)
        result.collectLatest {
            _data.value = _data.value + it
        }
        currentPage += 1;
    }

}