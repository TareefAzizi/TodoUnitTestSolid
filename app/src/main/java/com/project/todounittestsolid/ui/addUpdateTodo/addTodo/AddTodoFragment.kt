package com.project.todounittestsolid.ui.addUpdateTodo.addTodo

import androidx.fragment.app.viewModels
import com.project.todounittestsolid.ui.addUpdateTodo.BaseTodoFragment
import com.project.todounittestsolid.ui.addUpdateTodo.addTodo.viewModel.AddTodoViewModelImpl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddTodoFragment : BaseTodoFragment() {

    override val viewModel: AddTodoViewModelImpl by viewModels()

}
