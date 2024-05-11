package com.chunmaru.quizland.data.models


data class QuestionModel(
    val id: Int,
    val question: String,
    val image: String?,
    val category: String,
    val answers: List<String>,
    val correctAnswer: String,
    val score: Int
)