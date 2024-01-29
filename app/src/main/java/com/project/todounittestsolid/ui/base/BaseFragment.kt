package com.project.todounittestsolid.ui.base

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.asLiveData
import androidx.navigation.NavController
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar
import com.project.todounittestsolid.R
import com.project.todounittestsolid.ui.base.viewModel.BaseViewModel
import com.project.todounittestsolid.ui.base.viewModel.BaseViewModelImpl

abstract class BaseFragment<T: ViewBinding> : Fragment() {

    lateinit var binding: T
    abstract val viewModel: BaseViewModelImpl
    lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUIComponent()
        setupViewModelObserver()
    }

    open fun setupUIComponent(){}

    open fun setupViewModelObserver(){
        viewModel.error.asLiveData().observe(viewLifecycleOwner){
            showSnackBar(it,true)
        }
    }

    fun showSnackBar(msg: String, isError: Boolean = false){
        val snackBar = Snackbar.make(
            binding.root,
            msg,
            Snackbar.LENGTH_SHORT
        )
        if (isError) {
            snackBar.setBackgroundTint(
                ContextCompat.getColor(requireContext(), R.color.red)
            )
        }
        snackBar.show()
    }
}