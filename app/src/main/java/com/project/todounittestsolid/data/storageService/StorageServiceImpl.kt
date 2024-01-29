package com.project.todounittestsolid.data.storageService

import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.project.todounittestsolid.data.model.Todo

class StorageServiceImpl(
    private val sharedPref: SharedPreferences,
    private val gson: Gson = Gson()
): StorageService {
    override fun setBoolean(key: String, value: Boolean) {
        val editor = sharedPref.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    override fun removeBoolean(key: String) {
        val editor = sharedPref.edit()
        editor.remove(key)
        editor.apply()
    }

    override fun getBoolean(key: String): Boolean {
        return sharedPref.getBoolean(key, false)
    }

    override fun setString(key: String, value: String) {
        val editor = sharedPref.edit()
        editor.putString(key, value)
        editor.apply()
    }

    override fun removeString(key: String) {
        val editor = sharedPref.edit()
        editor.remove(key)
        editor.apply()
    }

    override fun getString(key: String): String {
        return sharedPref.getString(key, "") ?: ""
    }

    override fun setInt(key: String, value: Int) {
        val editor = sharedPref.edit()
        editor.putInt(key, value)
        editor.apply()
    }

    override fun removeInt(key: String) {
        val editor = sharedPref.edit()
        editor.remove(key)
        editor.apply()
    }

    override fun getInt(key: String): Int {
        return sharedPref.getInt(key, 0)
    }

    override fun setTodo(key: String, todo: Todo) {
        val todoString = gson.toJson(todo)
        setString(key, todoString)
    }

    override fun removeTodo(key: String) {
        removeString(key)
    }

    override fun getTodo(key: String): Todo? {
        val todoString = getString(key)
        if (todoString == "") return null

        val type = object : TypeToken<Todo>() {}.type
        return gson.fromJson(todoString, type)
    }
}
