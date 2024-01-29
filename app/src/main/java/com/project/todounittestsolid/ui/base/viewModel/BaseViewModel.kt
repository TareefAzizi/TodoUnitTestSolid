package com.project.todounittestsolid.ui.base.viewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow

interface BaseViewModel {
    val error: MutableSharedFlow<String>
    val finished: MutableSharedFlow<Unit>
}