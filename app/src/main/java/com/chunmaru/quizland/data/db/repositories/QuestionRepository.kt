package com.chunmaru.quizland.data.db.repositories

import com.chunmaru.quizland.data.models.QuestionModel

interface QuestionRepository {

    suspend fun getAllQuestion(): List<QuestionModel>

    suspend fun insertQuestion(questionModel: QuestionModel, onSuccess: () -> Unit)

    suspend fun deleteQuestion(questionModel: QuestionModel, onSuccess: () -> Unit)

    suspend fun getQuestionByCategory(category: String): List<QuestionModel>

    suspend fun getQuestionByCategoryLimit(category: String, limit: Int = 10): List<QuestionModel>

    suspend fun setsQuestionPassState(passedQuestion: List<Int>)

    suspend fun resetQuestionPass()

}