package com.chunmaru.quizland.data.db.repositories

interface QuestionRepositoryFactory {
    fun createQuestionRepository(): QuestionRepository
}