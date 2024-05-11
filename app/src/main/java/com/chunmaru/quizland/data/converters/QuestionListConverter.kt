package com.chunmaru.quizland.data.converters


import com.chunmaru.quizland.data.db.model.QuestionModelDb
import com.chunmaru.quizland.data.models.QuestionModel

class QuestionListConverter {

    private var modelConverter: QuestionModelConverter = QuestionModelConverter()

    fun getConverter(): QuestionModelConverter = modelConverter

    fun toQuestionsModelList(
        questionModelDbList: List<QuestionModelDb>
    ): List<QuestionModel> {
        return questionModelDbList.map { modelConverter.toQuestionModel(it) }
    }

    fun toQuestionModelDbList(
        questionModelList: List<QuestionModel>
    ): List<QuestionModelDb> {
        return questionModelList.map { modelConverter.toQuestionModelDb(it) }
    }
}
