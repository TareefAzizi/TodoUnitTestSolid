package com.project.todounittestsolid.ui.addUpdateTodo.addTodo

import com.project.todounittestsolid.data.model.Todo
import com.project.todounittestsolid.data.repo.TodoRepoImplMock
import com.project.todounittestsolid.ui.addUpdateTodo.addTodo.viewModel.AddTodoViewModel
import com.project.todounittestsolid.ui.addUpdateTodo.addTodo.viewModel.AddTodoViewModelImpl
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

class AddTodoViewModelTest {

    private lateinit var addTodoViewModel: AddTodoViewModel
    val repo = TodoRepoImplMock.getInstance()
    var testDispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        addTodoViewModel = AddTodoViewModelImpl(repo)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun cleanUp() {
        Dispatchers.resetMain()
    }
    @Test
    fun `after added 2 todo's, repo should return 4 of the todo's size`() = runTest {
        addTodoViewModel.submit("New todo", "New description")
        addTodoViewModel.submit("New todo #2", "New description #2")
        var todos = emptyList<Todo>()
        repo.getTodos().collect{
            todos = it
        }
        assertEquals(4, todos.size)
    }
}