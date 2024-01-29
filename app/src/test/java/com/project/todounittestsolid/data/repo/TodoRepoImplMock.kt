package com.project.todounittestsolid.data.repo

import com.project.todounittestsolid.data.model.Todo
import com.project.todounittestsolid.data.repository.TodoRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class TodoRepoImplMock : TodoRepo {
    private val todos = mutableMapOf(
        0 to Todo(title = "Title 1", desc = "Desc 1"),
        1 to Todo(title = "Title 2", desc = "Desc 2"),
    )
    private var counter = 2

    override fun getTodos(): Flow<List<Todo>> = flow {
        emit(todos.values.toList())
    }

    override fun getTodoById(id: Int): Todo? {
        return todos[id]
    }

    override fun addTodo(todo: Todo) {
        if (todo.id != null) {
            todos[todo.id!!] = todo
        } else {
            todos[counter] = todo.copy(id = counter)
            counter++
        }
    }

    override fun deleteTodo(todo: Todo) {
        todos.remove(todo.id)
    }

    companion object {
        private var instance: TodoRepoImplMock? = null

        fun getInstance(): TodoRepoImplMock {
            return instance ?: synchronized(this) {
                instance ?: TodoRepoImplMock().also { instance = it }
            }
        }
    }
}
