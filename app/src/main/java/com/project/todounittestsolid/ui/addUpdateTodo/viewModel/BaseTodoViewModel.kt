package com.project.todounittestsolid.ui.addUpdateTodo.viewModel

import androidx.lifecycle.ViewModel
import com.project.todounittestsolid.data.model.Todo
import com.project.todounittestsolid.ui.base.viewModel.BaseViewModel
import kotlinx.coroutines.flow.StateFlow

interface BaseTodoViewModel : BaseViewModel {
    val title: StateFlow<String>
    val desc: StateFlow<String>
    fun validateAndGetTodo(title: String, desc: String): Todo?
    fun submit(title: String, desc: String)
}
