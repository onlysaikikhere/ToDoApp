package com.example.glasstodo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.room.Room
import com.example.glasstodo.data.TaskDatabase
import com.example.glasstodo.theme.GlassTodoTheme
import com.example.glasstodo.ui.TodoScreen
import com.example.glasstodo.viewmodel.TaskViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val db = Room.databaseBuilder(
            applicationContext,
            TaskDatabase::class.java,
            "tasks.db"
        ).build()

        val viewModel = TaskViewModel(db.taskDao())

        setContent {
            GlassTodoTheme {
                TodoScreen(viewModel)
            }
        }
    }
}
