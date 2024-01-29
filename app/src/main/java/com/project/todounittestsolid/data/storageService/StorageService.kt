package com.project.todounittestsolid.data.storageService

import com.project.todounittestsolid.data.model.Todo

interface StorageService {
    fun setBoolean(key: String, value: Boolean)
    fun removeBoolean(key: String)
    fun getBoolean(key: String): Boolean
    fun setString(key: String, value: String)
    fun removeString(key: String)
    fun getString(key: String): String
    fun setInt(key: String, value: Int)
    fun removeInt(key: String)
    fun getInt(key: String): Int
    fun setTodo(key: String, todo: Todo)
    fun removeTodo(key: String)
    fun getTodo(key: String): Todo?
}
