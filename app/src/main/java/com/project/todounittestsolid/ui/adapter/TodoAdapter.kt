package com.project.todounittestsolid.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.todounittestsolid.data.model.Todo
import com.project.todounittestsolid.databinding.ItemLayoutTodoBinding

class TodoAdapter(
    private var todos: List<Todo>
) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    var listener: Listener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val binding = ItemLayoutTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TodoViewHolder(binding)
    }

    override fun getItemCount(): Int = todos.size

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(todos[position])
    }

    fun setTodos(todos: List<Todo>) {
        this.todos = todos
        notifyDataSetChanged()
    }

    inner class TodoViewHolder(val binding: ItemLayoutTodoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(todo: Todo) {
            binding.todo = todo
            binding.run {
                ivDelete.setOnClickListener {
                    listener?.onDeleteClick(todo)
                }
                llTodo.setOnClickListener {
                    listener?.onClick(todo)
                }
            }
        }
    }

    interface Listener {
        fun onClick(todo: Todo)
        fun onDeleteClick(todo: Todo)
    }
}
