package com.chunmaru.quizland.data.db.repositories


import android.util.Log
import com.chunmaru.quizland.data.converters.QuestionListConverter
import com.chunmaru.quizland.data.models.QuestionModel
import com.chunmaru.quizland.data.db.dao.QuestionDao
import java.lang.Exception

class QuestionRepositoryImpl(
    private val questionDao: QuestionDao,
    private val questionConverter: QuestionListConverter
) : QuestionRepository {

    override suspend fun getAllQuestion(): List<QuestionModel> {
        return questionConverter.toQuestionsModelList(
            questionDao.getAllQuestions()
        )
    }

    override suspend fun insertQuestion(questionModel: QuestionModel, onSuccess: () -> Unit) {
        try {
            questionDao.insert(questionConverter.getConverter().toQuestionModelDb(questionModel))
            onSuccess()
        } catch (e: Exception) {
            Log.d("MyTag", "insertQuestion: error : $e ")
        }
    }

    override suspend fun deleteQuestion(questionModel: QuestionModel, onSuccess: () -> Unit) {
        try {
            questionDao.delete(questionConverter.getConverter().toQuestionModelDb(questionModel))
            onSuccess()
        } catch (e: Exception) {
            Log.d("MyTag", "deleteQuestion: error : $e ")
        }
    }

    override suspend fun getQuestionByCategory(category: String): List<QuestionModel> {
        return if (category == "none") {
            questionConverter.toQuestionsModelList(questionDao.getQuestionWithOutCategory())
        } else {
            questionConverter.toQuestionsModelList(questionDao.getQuestionsByCategory(category))
        }
    }

    override suspend fun getQuestionByCategoryLimit(
        category: String,
        limit: Int
    ): List<QuestionModel> {
        return questionConverter.toQuestionsModelList(
            questionDao.getQuestionsByCategoryLimit(
                category,
                limit
            )
        )
    }

    override suspend fun setsQuestionPassState(passedQuestion: List<Int>) {
        try {
            questionDao.setQuestionsPass(passedQuestion)
        } catch (e: Exception) {
            Log.d("MyTag", "setsQuestionPassState: error : $e ")
        }
    }

    override suspend fun resetQuestionPass() {
        try {
            questionDao.resetQuestionPass()
        } catch (e: Exception) {
            Log.d("MyTag", "resetQuestionPass: $e ")
        }
    }
}