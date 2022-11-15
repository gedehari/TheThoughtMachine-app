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

    fun getNewThoughtEntry(title: String, author: String, message: String): Thought {
        return Thought(
            title = title,
            author = author,
            message = message
        )
    }

    fun addNewThought(title: String, author: String, message: String) {
        val newThought = getNewThoughtEntry(title, author, message)
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
