package com.project.todounittestsolid

import android.app.Application
import com.project.todounittestsolid.data.storageService.StorageService
import com.project.todounittestsolid.data.storageService.StorageServiceImpl
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication: Application() {
    lateinit var storageService: StorageService

    override fun onCreate() {
        super.onCreate()
        storageService = StorageServiceImpl(
            getSharedPreferences("todoPref", MODE_PRIVATE),
        )
    }
}