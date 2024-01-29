package com.project.todounittestsolid.core.di

import com.project.todounittestsolid.data.db.todo.TodosDatabase
import com.project.todounittestsolid.data.repository.TodoRepo
import com.project.todounittestsolid.data.repository.TodosRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepoModule {
    @Provides
    @Singleton
    fun provideTodosRepo(todosDatabase: TodosDatabase): TodoRepo = TodosRepoImpl(todosDatabase.dao)
}