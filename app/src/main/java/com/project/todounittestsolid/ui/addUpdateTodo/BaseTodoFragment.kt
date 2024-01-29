package com.project.todounittestsolid.ui.addUpdateTodo

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.NavHostFragment
import com.project.todounittestsolid.R
import com.project.todounittestsolid.databinding.FragmentAddUpdateTodoBinding
import com.project.todounittestsolid.ui.addUpdateTodo.viewModel.BaseTodoViewModel
import com.project.todounittestsolid.ui.addUpdateTodo.viewModel.BaseTodoViewModelImpl
import com.project.todounittestsolid.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class BaseTodoFragment : BaseFragment<FragmentAddUpdateTodoBinding>() {

    abstract override val viewModel: BaseTodoViewModelImpl

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddUpdateTodoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun setupUIComponent() {
        super.setupUIComponent()
        binding.run {
            lifecycleOwner = viewLifecycleOwner
            addTodoVM = viewModel
            btnSubmit.setOnClickListener { viewModel.submit(etTitle.text.toString(), etDesc.text.toString()) }
        }
    }

    override fun setupViewModelObserver() {
        super.setupViewModelObserver()
        viewModel.finished.asLiveData().observe(viewLifecycleOwner) {
            NavHostFragment.findNavController(this).popBackStack()
        }
    }
}
