package com.chunmaru.quizland.data.db.repositories


import android.util.Log
import com.chunmaru.quizland.data.models.QuestionModel
import com.chunmaru.quizland.data.db.dao.QuestionDao
import com.chunmaru.quizland.data.db.model.toQuestionsModel
import java.lang.Exception

class QuestionRepositoryImpl(
    private val questionDao: QuestionDao
) : QuestionRepository {

    override suspend fun getAllQuestion(): List<QuestionModel> {
        return questionDao.getAllQuestions().toQuestionsModel()
    }

    override suspend fun insertQuestion(questionModel: QuestionModel, onSuccess: () -> Unit) {
        try {
            questionDao.insert(questionModel.toQuestionModelDb())
            onSuccess()
        } catch (e: Exception) {
            Log.d("MyTag", "insertQuestion: error : $e ")
        }

    }

    override suspend fun deleteQuestion(questionModel: QuestionModel, onSuccess: () -> Unit) {
        try {
            questionDao.delete(questionModel.toQuestionModelDb())
            onSuccess()
        } catch (e: Exception) {
            Log.d("MyTag", "deleteQuestion: error : $e ")
        }
    }

    override suspend fun getQuestionByCategory(category: String): List<QuestionModel> {
        return if (category == "none") {
            questionDao.getQuestionWithOutCategory().toQuestionsModel()
        } else {
            questionDao.getQuestionsByCategory(category).toQuestionsModel()
        }

    }

    override suspend fun getQuestionByCategoryLimit(
        category: String,
        limit: Int
    ): List<QuestionModel> {
        return questionDao.getQuestionsByCategoryLimit(category, limit).toQuestionsModel()
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