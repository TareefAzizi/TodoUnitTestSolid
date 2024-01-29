package com.project.todounittestsolid.ui.home.viewModel

import androidx.lifecycle.ViewModel
import com.project.todounittestsolid.data.model.Todo
import kotlinx.coroutines.flow.Flow

interface HomeViewModel {
    fun getTodo(): Flow<List<Todo>>
    fun deleteTodo(todo: Todo)
}