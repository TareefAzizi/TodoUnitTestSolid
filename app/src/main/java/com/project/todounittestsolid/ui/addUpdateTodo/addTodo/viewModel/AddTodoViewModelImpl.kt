package com.project.todounittestsolid.ui.addUpdateTodo.addTodo.viewModel

import androidx.lifecycle.viewModelScope
import com.project.todounittestsolid.data.repository.TodoRepo
import com.project.todounittestsolid.ui.addUpdateTodo.viewModel.BaseTodoViewModelImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AddTodoViewModelImpl @Inject constructor(
    private val repo: TodoRepo
): BaseTodoViewModelImpl(), AddTodoViewModel {

    override fun submit(title: String, desc: String) {
        val todo = validateAndGetTodo(title, desc)
        todo?.let {
            viewModelScope.launch(Dispatchers.IO) {
                repo.addTodo(it)
                finished.emit(Unit)
            }
        }
    }
}