package com.project.todounittestsolid.ui.addUpdateTodo.updateTodo.viewModel

import androidx.lifecycle.ViewModel
import com.project.todounittestsolid.data.model.Todo
import com.project.todounittestsolid.ui.addUpdateTodo.viewModel.BaseTodoViewModel

interface UpdateTodoViewModel: BaseTodoViewModel {
    fun getTodo(todo: Todo)
    override fun submit(title: String, desc: String)
}
