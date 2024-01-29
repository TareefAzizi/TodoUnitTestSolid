package com.project.todounittestsolid.ui.home.viewModel

import androidx.lifecycle.viewModelScope
import com.project.todounittestsolid.data.model.Todo
import com.project.todounittestsolid.data.repository.TodoRepo
import com.project.todounittestsolid.ui.base.viewModel.BaseViewModelImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModelImpl @Inject constructor(
    private val repo: TodoRepo
) : BaseViewModelImpl(), HomeViewModel {

    val stateFlow: MutableStateFlow<String> = MutableStateFlow("stateflow")
    val sharedFlow: MutableSharedFlow<String> = MutableSharedFlow()

    override fun getTodo(): Flow<List<Todo>> {
        return repo.getTodos()
    }

    override fun deleteTodo(todo: Todo) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.deleteTodo(todo)
        }
    }
}
