package com.chunmaru.quizland.data.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.chunmaru.quizland.data.db.dao.Converters
import com.chunmaru.quizland.data.models.QuestionModel

@Entity(tableName = "question_table")
data class QuestionModelDb(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo val question: String,
    @ColumnInfo val image: String?,
    @ColumnInfo val category: String,
    @ColumnInfo val answers: String,
    @ColumnInfo val correctAnswer: String,
    @ColumnInfo val score: Int,
    @ColumnInfo val isPass: Boolean = false,
) {

    fun toQuestionModel(): QuestionModel {

        return QuestionModel(
            id = this.id,
            question = this.question,
            image = this.image ?: "default2",
            category = this.category,
            answers = Converters.fromJson(this.answers),
            correctAnswer = this.correctAnswer,
            score = this.score
        )
    }

}

fun List<QuestionModelDb>.toQuestionsModel(): List<QuestionModel> {
    return this.map { it.toQuestionModel() }
}
