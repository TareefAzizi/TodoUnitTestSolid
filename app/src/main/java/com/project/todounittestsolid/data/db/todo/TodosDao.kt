package com.project.todounittestsolid.data.db.todo

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.project.todounittestsolid.data.model.Todo
import kotlinx.coroutines.flow.Flow

@Dao
interface TodosDao {
    @Query("SELECT * FROM todo")
    fun getTodos(): Flow<List<Todo>>

    @Query("SELECT * FROM todo WHERE id = :id")
    fun getTodoById(id: Int): Todo?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTodo(todo: Todo)

    @Delete
    fun deleteTodo(todo: Todo)
}
