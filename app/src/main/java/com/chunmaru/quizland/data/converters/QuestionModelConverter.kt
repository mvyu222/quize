package com.chunmaru.quizland.data.converters

import com.chunmaru.quizland.data.db.model.QuestionModelDb
import com.chunmaru.quizland.data.models.QuestionModel

class QuestionModelConverter {

    fun toQuestionModelDb(questionModel: QuestionModel): QuestionModelDb {
        return QuestionModelDb(
            questionModel.id,
            questionModel.question,
            questionModel.image,
            questionModel.category,
            JsonConverters.toJson(questionModel.answers),
            questionModel.correctAnswer,
            questionModel.score
        )
    }

    fun toQuestionModel(questionModelDb: QuestionModelDb): QuestionModel {
        return QuestionModel(
            questionModelDb.id,
            questionModelDb.question,
            questionModelDb.image,
            questionModelDb.category,
            JsonConverters.fromJson(questionModelDb.answers),
            questionModelDb.correctAnswer,
            questionModelDb.score
        )
    }
}