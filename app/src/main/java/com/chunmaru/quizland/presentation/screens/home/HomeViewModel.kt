package com.chunmaru.quizland.presentation.screens.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chunmaru.quizland.data.db.repositories.QuestionRepository
import com.chunmaru.quizland.data.models.UserModel
import com.chunmaru.quizland.data.storage.IDataStorageManager
import com.chunmaru.quizland.data.storage.LoggingDataStorageManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val questionRepository: QuestionRepository,
    private val dataStorageManager: LoggingDataStorageManager
) : ViewModel() {


    private val _userData = MutableLiveData<UserModel>()
    val userData: LiveData<UserModel> = _userData

    init {
        viewModelScope.launch {
            dataStorageManager.getUser().collect {
                _userData.postValue(it)
            }

        }

    }


}