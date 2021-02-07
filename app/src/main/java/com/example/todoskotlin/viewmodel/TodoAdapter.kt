package com.example.todoskotlin.viewmodel

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.todoskotlin.databinding.ItemTodoBinding
import com.example.todoskotlin.model.Todo

class TodoAdapter : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val itemTodoBinding = ItemTodoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TodoViewHolder(itemTodoBinding)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.itemTodoBinding.apply {
            val todo = todos[position]
            textView.text = todo.title
            checkBox.isChecked = todo.completed
        }

    }

    override fun getItemCount() = todos.size

    // config de performance de exibicao da lista
    private val diffCallback =
        object : DiffUtil.ItemCallback<Todo>() {
            override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean {
                return oldItem == newItem
            }
        }

    private val differ = AsyncListDiffer(this, diffCallback)

    var todos: List<Todo>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }

    class TodoViewHolder(val itemTodoBinding: ItemTodoBinding) :
        RecyclerView.ViewHolder(itemTodoBinding.root)

}


