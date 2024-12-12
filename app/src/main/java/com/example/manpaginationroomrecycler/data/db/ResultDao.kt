package com.example.manpaginationroomrecycler.data.db
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.manpaginationroomrecycler.domain.model.Item
import kotlinx.coroutines.flow.Flow


@Dao
interface ResultDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: Item)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItems(items: List<Item>)

    @Query("SELECT * FROM items")
    fun getAllItems(): Flow<List<Item>>

    @Delete
    suspend fun deleteItem(item: Item)

}