package com.project.todounittestsolid.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.todounittestsolid.MyApplication
import com.project.todounittestsolid.data.model.Todo
import com.project.todounittestsolid.databinding.FragmentHomeBinding
import com.project.todounittestsolid.ui.adapter.TodoAdapter
import com.project.todounittestsolid.ui.base.BaseFragment
import com.project.todounittestsolid.ui.home.viewModel.HomeViewModelImpl
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    override val viewModel: HomeViewModelImpl by viewModels()
    private lateinit var adapter: TodoAdapter
    private val homeFragmentState = HomeFragmentState()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = NavHostFragment.findNavController(this)

        val counter = savedInstanceState?.getInt("counter") ?: 0
        homeFragmentState.counter.value = counter

        val storageService = (requireContext().applicationContext as MyApplication).storageService
        storageService.setString("abc", "smtg...")
    }

    override fun setupUIComponent() {
        super.setupUIComponent()
        setupAdapter()
        binding.efabAdd.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToAddTodoFragment()
            navController.navigate(action)
        }
    }

    override fun setupViewModelObserver() {
        super.setupViewModelObserver()
        lifecycleScope.launch {
            viewModel.getTodo().collect {
                adapter.setTodos(it)
                binding.llEmpty.isVisible = it.isEmpty()
            }
        }
        viewModel.stateFlow.asLiveData().observe(viewLifecycleOwner) {
            Log.d("flow", it)
        }
        viewModel.sharedFlow.asLiveData().observe(viewLifecycleOwner) {
            Log.d("flow", it)
        }
    }

    private fun setupAdapter() {
        adapter = TodoAdapter(emptyList())
        adapter.listener = object : TodoAdapter.Listener {
            override fun onClick(todo: Todo) {
                val action = HomeFragmentDirections.actionHomeFragmentToUpdateTodoFragment(todo)
                navController.navigate(action)
            }

            override fun onDeleteClick(todo: Todo) {
                viewModel.deleteTodo(todo)
            }
        }
        binding.rvTodos.adapter = adapter
        binding.rvTodos.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("counter", homeFragmentState.counter.value)
    }
}
