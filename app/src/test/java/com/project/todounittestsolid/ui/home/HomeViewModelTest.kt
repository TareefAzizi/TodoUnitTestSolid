package com.project.todounittestsolid.ui.home

import com.project.todounittestsolid.ui.home.viewModel.HomeViewModel
import app.cash.turbine.test
import com.project.todounittestsolid.data.model.Todo
import com.project.todounittestsolid.data.repo.TodoRepoImplMock
import com.project.todounittestsolid.ui.addUpdateTodo.updateTodo.viewModel.UpdateTodoViewModelImpl
import com.project.todounittestsolid.ui.home.viewModel.HomeViewModelImpl
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



class HomeViewModelTest {
    private lateinit var homeViewModel: HomeViewModel
    val testDispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        homeViewModel = HomeViewModelImpl(TodoRepoImplMock())
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun cleanUp() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getTodo should return 2 items`() = runTest {
        var todos = emptyList<Todo>()
        homeViewModel.getTodo().collect {
            todos = it
        }
        assertEquals(2, todos.size)
    }

    @Test
    fun `after deleting a todo, getTodos should return 1 item`() = runTest {
        homeViewModel.deleteTodo(Todo(id = 0, title = "Title 1", desc = "Description 1"))
        var todos = emptyList<Todo>()
        homeViewModel.getTodo().collect {
            todos = it
        }
        assertEquals(1, todos.size)
    }
}