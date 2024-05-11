package com.chunmaru.quizland.data.db.repositories

import com.chunmaru.quizland.data.converters.QuestionListConverter
import com.chunmaru.quizland.data.db.dao.QuestionDao

class DefaultQuestionRepositoryFactory(
    private val questionDao: QuestionDao,
    private val questionListConverter: QuestionListConverter,
) : QuestionRepositoryFactory {
    override fun createQuestionRepository(): QuestionRepository {
        return QuestionRepositoryImpl(questionDao, questionListConverter)
    }
}