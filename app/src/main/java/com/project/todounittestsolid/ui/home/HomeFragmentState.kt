package com.project.todounittestsolid.ui.home

import kotlinx.coroutines.flow.MutableStateFlow

class HomeFragmentState {
    var counter: MutableStateFlow<Int> = MutableStateFlow(0)

    fun increment(){
        counter.value++
    }
}