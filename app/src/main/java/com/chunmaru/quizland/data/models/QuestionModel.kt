package com.chunmaru.quizland.data.models

import com.chunmaru.quizland.data.db.dao.Converters
import com.chunmaru.quizland.data.db.model.QuestionModelDb


data class QuestionModel(
    val id: Int,
    val question: String,
    val image: String?,
    val category: String,
    val answers: List<String>,
    val correctAnswer: String,
    val score: Int
) {

    fun toQuestionModelDb(): QuestionModelDb {
        return QuestionModelDb(
            id,
            question,
            image,
            category,
            Converters.toJson(this.answers),
            correctAnswer,
            score
        )
    }

}