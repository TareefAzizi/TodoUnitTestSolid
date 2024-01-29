package com.project.todounittestsolid.ui.addUpdateTodo.updateTodo

import app.cash.turbine.test
import com.project.todounittestsolid.data.model.Todo
import com.project.todounittestsolid.data.repo.TodoRepoImplMock
import com.project.todounittestsolid.ui.addUpdateTodo.updateTodo.viewModel.UpdateTodoViewModelImpl
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

class UpdateTodoViewModelTest {
    private lateinit var updateTodoViewModel: UpdateTodoViewModelImpl
    val repo = TodoRepoImplMock.getInstance()
    var testDispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        updateTodoViewModel = UpdateTodoViewModelImpl(repo)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun cleanUp() {
        Dispatchers.resetMain()
    }

    @Test
    fun `after get todo, title should hold value of Title 1`() = runTest {
        updateTodoViewModel.getTodo(Todo(id = 0, title = "Title 1", desc = "Description 1"))
        updateTodoViewModel.title.test {
            val item = awaitItem()
            assertEquals("Title 1", item)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `after submit with todo 0 should return title equal Updated Todo`() = runTest {
        updateTodoViewModel.getTodo(Todo(id = 0, title = "Title 1", desc = "Description 1"))
        updateTodoViewModel.submit("Updated todo", "Updated description")
        advanceUntilIdle()
        assertEquals("Updated todo", repo.getTodoById(0)!!.title)
    }
}
