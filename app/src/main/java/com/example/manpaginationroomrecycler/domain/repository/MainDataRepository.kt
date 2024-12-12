package com.example.manpaginationroomrecycler.domain.repository

import android.util.Log
import com.example.manpaginationroomrecycler.data.db.ResultDatabase
import com.example.manpaginationroomrecycler.data.network.MainAPI
import com.example.manpaginationroomrecycler.domain.model.Item
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MainDataRepository @Inject constructor(
    private val mainAPI: MainAPI,
    private val db: ResultDatabase
) {

    fun getAllItems(): Flow<List<Item>> = db.getResultDao().getAllItems()

    suspend fun fetchItemsFromServer(page: Int): Flow<List<Item>> = flow {
        try {
            val response = mainAPI.getData(page)
            if (response.isSuccessful) {
                response.body()?.let {
                    db.getResultDao().insertItems(it.results)
                    emit(it.results)
                }
            }
        } catch (e: Exception) {
            Log.e("some", e.toString())
        }
    }
}