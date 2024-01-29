package com.project.todounittestsolid.ui.base.viewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow

abstract class BaseViewModelImpl : ViewModel(), BaseViewModel {
    override val error: MutableSharedFlow<String> = MutableSharedFlow()
    override val finished: MutableSharedFlow<Unit> = MutableSharedFlow()
}