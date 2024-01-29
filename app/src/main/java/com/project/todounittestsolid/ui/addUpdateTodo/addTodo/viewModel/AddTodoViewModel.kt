package com.project.todounittestsolid.ui.addUpdateTodo.addTodo.viewModel

import com.project.todounittestsolid.ui.addUpdateTodo.viewModel.BaseTodoViewModel

interface AddTodoViewModel: BaseTodoViewModel {
    override fun submit(title:String, desc: String)
}