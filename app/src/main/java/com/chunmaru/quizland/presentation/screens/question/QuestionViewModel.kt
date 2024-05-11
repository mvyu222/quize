package com.chunmaru.quizland.presentation.screens.question

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chunmaru.quizland.data.db.repositories.QuestionRepository
import com.chunmaru.quizland.data.storage.DataStorageManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class QuestionViewModel @Inject constructor(
    private val questionRepository: QuestionRepository,
    private val dataStorageManager: DataStorageManager
) : ViewModel() {

    private var categoryHasBeenSet = false

    private val _state: MutableLiveData<QuestionState> = MutableLiveData(QuestionState.Initial)
    val state: LiveData<QuestionState> = _state

    private val questionsCompleted = mutableListOf<Int>()
    private var score = 0

    fun setCategory(category: String) {
        if (!categoryHasBeenSet) {
            init(category)
            categoryHasBeenSet = true
        }
    }

    private fun init(category: String) {
        _state.value = QuestionState.Pending
        viewModelScope.launch(Dispatchers.IO) {
            delay(2000)
            val questions = questionRepository.getQuestionByCategory(category)
            if (questions.isNotEmpty()) {
                _state.postValue(
                    QuestionState.ShowQuestions(
                        questions,
                        0
                    )
                )
            } else {
                _state.postValue(QuestionState.QuestionsCompleted)
            }

        }
    }

    fun onAnswerClick(answer: String) {
        val currentState = _state.value as? QuestionState.ShowQuestions ?: return

        val index = currentState.currentQuestion
        if (answer == currentState.question[index].correctAnswer) {
            score += currentState.question[index].score
            questionsCompleted.add(currentState.question[index].id)
        }

    }

    fun getNextQuestion() {
        val currentState = _state.value as? QuestionState.ShowQuestions ?: return

        if (currentState.question.size <= currentState.currentQuestion + 1) {
            _state.value = QuestionState.QuestionEnd(score)
        } else {
            _state.value = currentState.copy(currentQuestion = currentState.currentQuestion + 1)
        }

    }

    fun goBackWithSaveResult(onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {

            questionRepository.setsQuestionPassState(questionsCompleted)
            dataStorageManager.addPoints(score)

            withContext(Dispatchers.Main) { onSuccess() }
        }
    }

}