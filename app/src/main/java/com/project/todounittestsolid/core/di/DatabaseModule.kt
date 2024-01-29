package com.project.todounittestsolid.core.di

import android.content.Context
import androidx.room.Room
import com.project.todounittestsolid.data.db.todo.TodosDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): TodosDatabase = Room.databaseBuilder(context,
        TodosDatabase::class.java, TodosDatabase.name)
        .addMigrations(TodosDatabase.MIGRATION_2_3)
        .build()
}