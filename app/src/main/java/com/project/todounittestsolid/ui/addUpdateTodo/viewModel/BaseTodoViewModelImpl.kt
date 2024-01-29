package com.project.todounittestsolid.ui.addUpdateTodo.viewModel

import androidx.lifecycle.viewModelScope
import com.project.todounittestsolid.core.utils.Validation
import com.project.todounittestsolid.data.model.FieldAndArg
import com.project.todounittestsolid.data.model.Todo
import com.project.todounittestsolid.ui.base.viewModel.BaseViewModelImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class BaseTodoViewModelImpl : BaseViewModelImpl(), BaseTodoViewModel {

    val _title: MutableStateFlow<String> = MutableStateFlow("")
    override val title: StateFlow<String> = _title

    val _desc: MutableStateFlow<String> = MutableStateFlow("")
    override val desc: StateFlow<String> = _desc

    override fun validateAndGetTodo(title: String, desc: String): Todo? {
        val checked = Validation.validate(
            FieldAndArg(name = "Title", value = title, reg = "^(?=.*[A-Za-z0-9]).{3,20}\$"),
            FieldAndArg(name = "Description", value = desc, reg = "^(?=.*[A-Za-z0-9]).{3,}\$")
        )
        if (checked.first) return Todo(title = title, desc = desc)
        viewModelScope.launch {
            finished.emit(Unit)
            error.emit(checked.second)
        }
        return null
    }
}
