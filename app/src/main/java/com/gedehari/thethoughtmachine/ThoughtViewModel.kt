package com.gedehari.thethoughtmachine

import android.util.Log
import androidx.lifecycle.*
import com.gedehari.thethoughtmachine.data.Thought
import com.gedehari.thethoughtmachine.data.ThoughtDao
import com.gedehari.thethoughtmachine.network.ThoughtsApi
import kotlinx.coroutines.launch

class ThoughtViewModel(private val thoughtDao: ThoughtDao) : ViewModel() {
    val allThoughts: LiveData<List<Thought>> = thoughtDao.getThoughts().asLiveData()

    fun addNewThought(id: Int, title: String, author: String, message: String, createdAt: Long) {
        val newThought = getNewThoughtEntry(id, title, author, message, createdAt)
        insertThought(newThought)
    }

    fun getLatestThoughts(callback: () -> Unit) {
        viewModelScope.launch {
            val reqThoughts = ThoughtsApi.retrofitService.getThoughts()
            val filteredThoughts = reqThoughts.toMutableList()
            val allThoughtsValue = allThoughts.value!!
            reqThoughts.forEach { reqThought ->
                allThoughtsValue.forEach { curThought ->
                    if (reqThought.id == curThought.id)
                        filteredThoughts.remove(reqThought)
                }
            }

            filteredThoughts.forEach { filteredThought ->
                insertThought(filteredThought)
            }

            Log.i("TTM", "Got ${filteredThoughts.size} new thoughts.")
            callback.invoke()
        }
    }

    fun getPastThoughts(callback: () -> Unit) {
        viewModelScope.launch {
            val reqThoughts = ThoughtsApi.retrofitService.getThoughts(allThoughts.value!!.last().id)
            reqThoughts.forEach { thought ->
                insertThought(thought)
            }

            Log.i("TTM", "Got ${reqThoughts.size} new thoughts.")
            callback.invoke()
        }
    }

    private fun insertThought(thought: Thought) {
        viewModelScope.launch {
            thoughtDao.insert(thought)
        }
    }

    private fun getNewThoughtEntry(
        id: Int,
        title: String,
        author: String,
        message: String,
        createdAt: Long
    ): Thought {
        return Thought(
            id = id,
            title = title,
            author = author,
            message = message,
            createdAt = createdAt
        )
    }
}

class ThoughtViewModelFactory(private val thoughtDao: ThoughtDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ThoughtViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ThoughtViewModel(thoughtDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
