package com.gedehari.thethoughtmachine

import androidx.lifecycle.*
import com.gedehari.thethoughtmachine.data.Thought
import com.gedehari.thethoughtmachine.data.ThoughtDao
import kotlinx.coroutines.launch

class ThoughtViewModel(private val thoughtDao: ThoughtDao): ViewModel() {
    val allThoughts: LiveData<List<Thought>> = thoughtDao.getThoughts().asLiveData()

    fun insertThought(thought: Thought) {
        viewModelScope.launch {
            thoughtDao.insert(thought)
        }
    }

    fun getNewThoughtEntry(id: Int, title: String, author: String, message: String, createdAt: Long): Thought {
        return Thought(
            id = id,
            title = title,
            author = author,
            message = message,
            createdAt = createdAt
        )
    }

    fun addNewThought(id: Int, title: String, author: String, message: String, createdAt: Long) {
        val newThought = getNewThoughtEntry(id, title, author, message, createdAt)
        insertThought(newThought)
    }
}

class ThoughtViewModelFactory(private val thoughtDao: ThoughtDao): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ThoughtViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ThoughtViewModel(thoughtDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
