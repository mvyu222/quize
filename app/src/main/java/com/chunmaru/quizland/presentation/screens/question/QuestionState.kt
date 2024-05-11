package com.chunmaru.quizland.presentation.screens.question

import com.chunmaru.quizland.data.models.QuestionModel

sealed class QuestionState {

    data object Initial : QuestionState()

    data object Pending : QuestionState()

    data object QuestionsCompleted : QuestionState()

    data class ShowQuestions(
        val question: List<QuestionModel>,
        val currentQuestion: Int = 0
    ) : QuestionState()

    data class QuestionEnd(
        val point: Int
    ) : QuestionState()


}