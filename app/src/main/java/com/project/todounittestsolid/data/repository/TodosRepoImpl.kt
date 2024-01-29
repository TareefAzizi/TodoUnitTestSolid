package com.project.todounittestsolid.data.repository

import com.project.todounittestsolid.data.db.todo.TodosDao
import com.project.todounittestsolid.data.model.Todo
import kotlinx.coroutines.flow.Flow

class TodosRepoImpl(private val dao: TodosDao) : TodoRepo {

    override fun getTodos(): Flow<List<Todo>> {
        return dao.getTodos()
    }

    override fun getTodoById(id: Int): Todo? {
        return dao.getTodoById(id)
    }

    override fun addTodo(todo: Todo) {
        return dao.insertTodo(todo)
    }

    override fun deleteTodo(todo: Todo) {
        dao.deleteTodo(todo)
    }
}
