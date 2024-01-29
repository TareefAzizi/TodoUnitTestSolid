package com.project.todounittestsolid.ui.addUpdateTodo.updateTodo.viewModel

import androidx.lifecycle.viewModelScope
import com.project.todounittestsolid.data.model.Todo
import com.project.todounittestsolid.data.repository.TodoRepo
import com.project.todounittestsolid.ui.addUpdateTodo.viewModel.BaseTodoViewModelImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdateTodoViewModelImpl @Inject constructor(
    private val repo: TodoRepo
): BaseTodoViewModelImpl(), UpdateTodoViewModel {

    var id: Int? = null

    override fun getTodo(todo: Todo) {
        _title.value = todo.title
        _desc.value = todo.desc
        id = todo.id
    }

    override fun submit(title: String, desc: String) {
        val todo = validateAndGetTodo(title, desc)
        todo?.let {
            viewModelScope.launch(Dispatchers.IO) {
                repo.addTodo(it.copy(id = id))
                finished.emit(Unit)
            }
        }
    }
}
