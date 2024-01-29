package com.project.todounittestsolid.ui.addUpdateTodo.updateTodo

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.project.todounittestsolid.R
import com.project.todounittestsolid.ui.addUpdateTodo.BaseTodoFragment
import com.project.todounittestsolid.ui.addUpdateTodo.updateTodo.viewModel.UpdateTodoViewModel
import com.project.todounittestsolid.ui.addUpdateTodo.updateTodo.viewModel.UpdateTodoViewModelImpl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateTodoFragment : BaseTodoFragment() {

    override val viewModel: UpdateTodoViewModelImpl by viewModels()
    private val args: UpdateTodoFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getTodo(args.todo)
        binding.run {
            tvAddUpdTodo.text = getString(R.string.upd_todo)
            btnSubmit.text = getString(R.string.update)
        }
    }
}
