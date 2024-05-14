package com.chunmaru.quizland.presentation.screens.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chunmaru.quizland.data.db.repositories.QuestionRepository
import com.chunmaru.quizland.data.models.UserModel
import com.chunmaru.quizland.data.storage.IDataStorageManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val dataStorageManager: IDataStorageManager,
    private val questionRepository: QuestionRepository
) : ViewModel() {


    private val _userData = MutableLiveData<UserModel>()
    val userData: LiveData<UserModel> = _userData

    init {
        viewModelScope.launch(Dispatchers.IO)  {
            dataStorageManager.getUser().collect {
                _userData.postValue(it)
            }

        }
    }

    fun setNewName(name: String) {
        viewModelScope.launch(Dispatchers.IO)  {
            dataStorageManager.setUserName(name)
            _userData.value = _userData.value?.copy(name = name)
        }
    }

    fun setNewIcon(icon: String) {
        viewModelScope.launch(Dispatchers.IO)  {
            dataStorageManager.setUserIcon(icon)
            _userData.value = _userData.value?.copy(pic = icon)
        }
    }

    fun resetUserScore() {
        viewModelScope.launch(Dispatchers.IO) {
            dataStorageManager.resetPoint()
            questionRepository.resetQuestionPass()

            _userData.postValue(_userData.value?.copy(score = 0))
        }
    }


}