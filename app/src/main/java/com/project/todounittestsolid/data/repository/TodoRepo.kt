package com.project.todounittestsolid.data.repository

import com.project.todounittestsolid.data.model.Todo
import kotlinx.coroutines.flow.Flow

interface TodoRepo {
    fun getTodos(): Flow<List<Todo>>

    fun getTodoById(id: Int): Todo?

    fun addTodo(todo: Todo)

    fun deleteTodo(todo: Todo)
}
