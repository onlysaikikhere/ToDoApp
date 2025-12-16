package com.example.glasstodo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.glasstodo.data.Task
import com.example.glasstodo.data.TaskDao
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TaskViewModel(private val dao: TaskDao) : ViewModel() {

    val tasks = dao.observeTasks()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    fun addTask(title: String) {
        if (title.isBlank()) return
        viewModelScope.launch {
            dao.insert(Task(title = title.trim()))
        }
    }

    fun toggle(task: Task) {
        viewModelScope.launch {
            dao.update(task.copy(completed = !task.completed))
        }
    }

    fun delete(task: Task) {
        viewModelScope.launch {
            dao.delete(task)
        }
    }
}
